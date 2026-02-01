package logisticspipes.renderer.newpipe;

import net.minecraft.client.renderer.GLAllocation;

import org.lwjgl.opengl.GL11;

public class GLRenderList implements IRenderable {

    private final int listID = GLAllocation.generateDisplayLists(1);
    public boolean isValid = true;
    private long lastUsed = System.currentTimeMillis();
    private boolean isFilled = false;

    @Override
    public int getID() {
        return listID;
    }

    @Override
    public void startListCompile() {
        if (!isValid) {
            throw new UnsupportedOperationException("Can't use a removed list");
        }
        GL11.glNewList(listID, GL11.GL_COMPILE);
    }

    @Override
    public void stopCompile() {
        if (!isValid) {
            throw new UnsupportedOperationException("Can't use a removed list");
        }
        GL11.glEndList();
        isFilled = true;
    }

    @Override
    public void render() {
        if (!isValid) {
            throw new UnsupportedOperationException("Can't use a removed list");
        }
        GL11.glCallList(listID);
        lastUsed = System.currentTimeMillis();
    }

    @Override
    public boolean check() {
        if (!isValid) {
            return true;
        }
        if (lastUsed + 1000 * 60 < System.currentTimeMillis()) {
            isValid = false;
            return false;
        }
        return true;
    }

    @Override
    public boolean isInvalid() {
        return !isValid;
    }

    @Override
    public boolean isFilled() {
        return isFilled;
    }

    @Override
    public void close() {
        GLAllocation.deleteDisplayLists(listID);
        isValid = false;
    }
}

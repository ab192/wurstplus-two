package me.travis.wurstplus.wurstplustwo.hacks.movement;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class WurstplusStep extends WurstplusHack {

    public WurstplusStep() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);

        this.name = "Step";
        this.tag = "Step";
        this.description = "Move up / down block big";
    }

    WurstplusSetting mode = create("Mode", "StepMode", "Normal", combobox("Normal", "Vanilla"));


    @Override
    public void disable() {
        if ((mc.player == null) || (mc.world == null)) return;
        mc.player.stepHeight = 0.6F;
    }


    @Override
    public void update() {
        if ((mc.player == null) || (mc.world == null)) return;
        if (mode.in("Normal")) {
            if ((mc.player.moveForward != 0) && (mc.player.moveStrafing != 0) && mc.player.collidedHorizontally) {
                if (mc.player.onGround && !mc.player.isOnLadder() && !mc.player.isInWater() && !mc.player.isInLava() && !mc.player.movementInput.jump && !mc.player.noClip) {
                    double n = getNormal();
                    if ((n < 0.0D) || (n > 2.0D)) {
                        return;
                    }
                    if (n == 2.0D) {
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.42, mc.player.posZ, mc.player.onGround));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.78, mc.player.posZ, mc.player.onGround));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.63, mc.player.posZ, mc.player.onGround));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.51, mc.player.posZ, mc.player.onGround));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.9, mc.player.posZ, mc.player.onGround));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.21, mc.player.posZ, mc.player.onGround));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.45, mc.player.posZ, mc.player.onGround));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.43, mc.player.posZ, mc.player.onGround));
                        mc.player.setPosition(mc.player.posX, mc.player.posY + 2.0, mc.player.posZ);
                    } else if (n == 1.5D) {
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.41999998688698, mc.player.posZ, mc.player.onGround));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.7531999805212, mc.player.posZ, mc.player.onGround));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.00133597911214, mc.player.posZ, mc.player.onGround));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.16610926093821, mc.player.posZ, mc.player.onGround));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.24918707874468, mc.player.posZ, mc.player.onGround));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.1707870772188, mc.player.posZ, mc.player.onGround));
                        mc.player.setPosition(mc.player.posX, mc.player.posY + 1.0, mc.player.posZ);
                    } else if (n == 1.0D) {
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.41999998688698, mc.player.posZ, mc.player.onGround));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.7531999805212, mc.player.posZ, mc.player.onGround));
                        mc.player.setPosition(mc.player.posX, mc.player.posY + 1.0, mc.player.posZ);
                    }
                }
            }
        } else if (mode.in("Vanilla")) {
            mc.player.stepHeight = 2.0F;
        }
    }


    public double getNormal() {
        mc.player.stepHeight = 0.6F;
        AxisAlignedBB grow = mc.player.getEntityBoundingBox().offset(0, 0.05, 0).grow(0.05);

        if (!mc.world.getCollisionBoxes(mc.player, grow.offset(0, 2, 0)).isEmpty()) {
            return 100;
        }
        double maxY = -1;
        for (AxisAlignedBB aabb : mc.world.getCollisionBoxes(mc.player, grow)) {
            if (aabb.maxY > maxY) {
                maxY = aabb.maxY;
            }
        }
        return maxY - mc.player.posY;
    }
}

package com.raoulvdberge.refinedstorage.container;

import com.raoulvdberge.refinedstorage.RSContainers;
import com.raoulvdberge.refinedstorage.container.slot.filter.FilterSlot;
import com.raoulvdberge.refinedstorage.container.slot.filter.FluidFilterSlot;
import com.raoulvdberge.refinedstorage.tile.ExternalStorageTile;
import com.raoulvdberge.refinedstorage.tile.config.IType;
import net.minecraft.entity.player.PlayerEntity;

public class ExternalStorageContainer extends BaseContainer {
    public ExternalStorageContainer(ExternalStorageTile externalStorage, PlayerEntity player, int windowId) {
        super(RSContainers.EXTERNAL_STORAGE, externalStorage, player, windowId);

        for (int i = 0; i < 9; ++i) {
            addSlot(new FilterSlot(externalStorage.getNode().getItemFilters(), i, 8 + (18 * i), 20).setEnableHandler(() -> externalStorage.getNode().getType() == IType.ITEMS));
        }

        for (int i = 0; i < 9; ++i) {
            addSlot(new FluidFilterSlot(externalStorage.getNode().getFluidFilters(), i, 8 + (18 * i), 20).setEnableHandler(() -> externalStorage.getNode().getType() == IType.FLUIDS));
        }

        addPlayerInventory(8, 141);

        transferManager.addFilterTransfer(player.inventory, externalStorage.getNode().getItemFilters(), externalStorage.getNode().getFluidFilters(), externalStorage.getNode()::getType);
    }
}

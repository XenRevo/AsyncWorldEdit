/*
 * The MIT License
 *
 * Copyright 2013 SBPrime.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.primesoft.asyncworldedit.worldedit;

import org.primesoft.asyncworldedit.ConfigProvider;
import org.primesoft.asyncworldedit.PluginMain;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.EditSessionFactory;
import com.sk89q.worldedit.LocalPlayer;
import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.bags.BlockBag;
import org.primesoft.asyncworldedit.blocklogger.IBlockLogger;
import org.primesoft.asyncworldedit.blocklogger.NoneLogger;

/**
 *
 * @author SBPrime
 */
public class AsyncEditSessionFactory extends EditSessionFactory {

    private PluginMain m_parent;
    
    /**
     * The block logger
     */
    private IBlockLogger m_logger;
           
    
    public AsyncEditSessionFactory(PluginMain parent, IBlockLogger logger) {
        m_parent = parent;
        setLogger(logger);
    }

    /**
     * Set the logger
     *
     * @param logger
     */
    public void setLogger(IBlockLogger logger) {
        if (logger == null) {
            logger = new NoneLogger();
        }
        m_logger = logger;
    }
    
    /**
     * Get the logger
     *
     * @return logger
     */
    public IBlockLogger getLogger() {
        return m_logger;
    }
    
    @Override
    public EditSession getEditSession(LocalWorld world, int maxBlocks) {
        return new AsyncEditSession(this, m_parent, ConfigProvider.DEFAULT_USER, world, maxBlocks);
    }

    @Override
    public EditSession getEditSession(LocalWorld world, int maxBlocks, LocalPlayer player) {
        return new AsyncEditSession(this, m_parent, player.getName(), world, maxBlocks);
    }

    @Override
    public EditSession getEditSession(LocalWorld world, int maxBlocks, BlockBag blockBag) {
        return new AsyncEditSession(this, m_parent, ConfigProvider.DEFAULT_USER , world, maxBlocks, blockBag);
    }

    @Override
    public EditSession getEditSession(LocalWorld world, int maxBlocks, BlockBag blockBag,
            LocalPlayer player) {
        return new AsyncEditSession(this, m_parent, player.getName(), world, maxBlocks, blockBag);
    }
}

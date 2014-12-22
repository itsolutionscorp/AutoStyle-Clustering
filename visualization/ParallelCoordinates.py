#!/usr/bin/env python

'''Adapted from http://stackoverflow.com/questions/8230638/parallel-coordinates-plot-in-matplotlib by VividD
'''
import matplotlib.pyplot as plt
import matplotlib.ticker as ticker
import numpy as np
import argparse

def parallel_coordinates(data_sets, style=None):
    Y_LIM = .01

    dims = len(data_sets[0])
    x    = range(dims)
    fig, axes = plt.subplots(1, dims-1, sharey=False)

    if style is None:
        style = ['r-']*len(data_sets)

    # Calculate the limits on the data
    min_max_range = list()
    for m in zip(*data_sets):
        mn = min(m)
        mx = max(m)
        if mn == mx:
            mn -= 0.5
            mx = mn + 1.
        r  = float(mx - mn)
        min_max_range.append((mn, mx, r))

    # Normalize the data sets
    norm_data_sets = list()
    for ds in data_sets:
        nds = [(value - min_max_range[dimension][0]) / 
                min_max_range[dimension][2] 
                for dimension,value in enumerate(ds)]
        norm_data_sets.append(nds)
    #data_sets = norm_data_sets

    # Plot the datasets on all the subplots
    for i, ax in enumerate(axes):
        for dsi, d in enumerate(data_sets):
            ax.plot(x, d, style[dsi])
            ax.set_ylim([0,Y_LIM])
        ax.set_xlim([x[i], x[i+1]])

    # Set the x axis ticks 
    for dimension, (axx,xx) in enumerate(zip(axes, x[:-1])):
        axx.xaxis.set_major_locator(ticker.FixedLocator([xx]))
        ticks = len(axx.get_yticklabels())
        labels = list()
        step=Y_LIM/(ticks-2)#step = min_max_range[dimension][2] / (ticks - 1) #swap dimension for 0
        mn=0#mn   = min_max_range[dimension][0]
        for i in xrange(ticks):
            v = mn + i*step
            labels.append('%4.6f' % v)
        axx.set_yticklabels(labels)


    # Move the final axis' ticks to the right-hand side
    axx = plt.twinx(axes[-1])
    dimension += 1
    axx.xaxis.set_major_locator(ticker.FixedLocator([x[-2], x[-1]]))
    ticks = len(axx.get_yticklabels())
    step=Y_LIM/(ticks-1)#step = min_max_range[dimension][2] / (ticks - 1)
    mn=0#mn   = min_max_range[dimension][0]
    labels = ['%4.6f' % (mn + i*step) for i in xrange(ticks)]
    axx.set_yticklabels(labels)

    # Stack the subplots 
    plt.subplots_adjust(wspace=0)

    return plt


if __name__ == '__main__':

    parser = argparse.ArgumentParser(description='Parallel coordinates plotting.')
    parser.add_argument('data', help='Name of file that contains data to plot.')
    args = parser.parse_args()
    data = np.loadtxt(args.data)
    colors = ['b'] * data.size[0]
    parallel_coordinates(data, style=colors).show()
#!/usr/bin/env python

import argparse
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.cm as cm

def compute_outliers(data, bins, cutoff=.9):
    hist, bin_edges = np.histogram(data, bins)
    total_points = np.sum(hist)
    points_sofar = 0.0
    edge_num = 0
    while (cutoff_fraction < cutoff):
        points_sofar += hist[edge_num]
        cutoff_fraction = points_sofar/total_points
        edge_num += 1
    return data > bin_edges[edge_num]

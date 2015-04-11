#!/usr/bin/env python3
import math

def on_square(square):
    return int(math.pow(2, square-1))

def total_after(square):
    return sum(map(on_square, [square for square in range(1, square+1)]))

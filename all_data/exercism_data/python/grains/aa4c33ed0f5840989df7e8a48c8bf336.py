#!/usr/bin/python3 -V
# -*- coding: utf-8 -*-def on_square(cell_num):    return 2 ** (cell_num - 1)def total_after(cell_num):    if cell_num == 1:        return 1    else:        return (2 ** cell_num) - 1

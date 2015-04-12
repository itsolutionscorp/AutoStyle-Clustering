#!/usr/bin/ruby
# encoding: utf-8
#
#  hamming.rb
#
#  Created by Dan MacLean (TSL) on 2014-09-25.
#  Copyright (c). All rights reserved.
#

class Hamming
  
  def compute a, b
    hamming_distance = 0
    longest = a.length > b.length ? a.length : b.length
    (0..longest-1).each do |i|
      next if a[i].nil? or b[i].nil?
      hamming_distance = hamming_distance + 1 if a[i] != b[i]
    end
    hamming_distance
  end
  
end

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
    [a.length, b.length].min.times.count  { |i| a[i] != b[i] }
  end
  
end

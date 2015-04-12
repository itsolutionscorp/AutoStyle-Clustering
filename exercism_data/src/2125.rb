#!/usr/bin/env ruby

class Hamming
  def compute(a, b)
    count = 0
    a = a.split('')
    b = b.split('')
    a.size.times do |x|
      count += 1 if !a[x].eql? b[x]
    end
    count
  end
end

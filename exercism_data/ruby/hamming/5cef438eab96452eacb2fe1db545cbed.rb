#!/usr/bin/ruby

class Hamming
  def self.compute(first, second)
    arr_one = first.split(//)
    arr_two = second.split(//)
    i = 0
    h = 0
    arr_one.each do |l|
      if !(arr_two[i])
        return h
      end
      if !(l == arr_two[i])
        h = h + 1
      end
    i = i + 1
    end
  return h
  end
end

#!/usr/bin/ruby

class Hamming

  def self.compare(a, b)

    #print "\t", a, " ", b, " => ", a.ord, " ", b.ord, "\n"


    return 0 if a == b
    return 1 if a > b
    return 1 if b > a
  end

  def self.compute(str1, str2)
    count = 0
    str1 = str1.split('')
    str2 = str2.split('')
    str1.map.with_index { |l, i| count += compare(l, str2[i]) }
    count
    #print "\t", count, "\n"
  end
end

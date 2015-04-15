#!/usr/bin/ruby
#
class Hamming

  def self.compute(strand1, strand2)
    count = 0
    matches_count = Hash.new(0)
    list1 = strand1.chars
    list2 = strand2.chars
    if list1.length < list2.length
      matches = list1.zip(list2).map { |x, y| x == y }
    else matches = list2.zip(list1).map { |x, y| x == y }
    end
    matches.each do |num|
      matches_count[num] += 1
    end
  count = matches_count[false]
  return count
  end
end

#!/usr/bin/env ruby

class Hamming
  def compute(string1,string2)
    score = 0
    string1.chars.each_with_index do |char, i|
      if char != string2[i]
        score += 1
      end
    end
    score
  end
end

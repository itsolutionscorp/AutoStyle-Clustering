require 'pry'
class Hamming
  def self.compute(s1, s2)
    short_strand = s1.size > s2.size ? s2 : s1
    long_strand = s1.size > s2.size ? s1 : s2

    count = 0
    index = 0
    short_strand.each_char do |c|
      count += 1 if c != long_strand[index]
      index += 1
    end


    count
  end
end

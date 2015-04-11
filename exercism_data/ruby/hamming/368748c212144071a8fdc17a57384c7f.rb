require 'pry'

class Hamming
  def self.compute(strand1, strand2)
    strand1.each_char.with_index.count do |char, i|
      char != strand2[i]
    end
  end
end

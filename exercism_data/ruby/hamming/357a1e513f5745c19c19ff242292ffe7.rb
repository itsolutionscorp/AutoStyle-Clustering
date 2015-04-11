require 'pry'
class Hamming
  def self.compute(dna, dnb)
    lines = [dna, dnb].sort_by {|sequence| sequence.length }
    lines[0].chars.zip(lines[1].chars).select { |(a, b)| a != b }.count
  end
end

class Hamming
  def self.compute(dna, dnb)
    first, second = [dna, dnb].sort_by {|sequence| sequence.length }
    first.chars.zip(second.chars).select { |(a, b)| a != b }.count
  end
end

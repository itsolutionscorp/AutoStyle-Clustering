class Hamming
  def self.compute(first_dna, second_dna)
    first_dna   = first_dna.chars
    second_dna  = second_dna.chars

    combine(first_dna, second_dna).count {|pair| pair[0] != pair[1]}
  end

  private
  def self.combine(first, second)
    if first.length < second.length
      first.zip(second)
    else
      second.zip(first)
    end
  end
end

module Hamming
  def self.compute(original_strand, sample)
    pair_strands(original_strand, sample).count { |base1, base2|
      base1 && base2 && base1 != base2
    }
  end

  private

  def self.pair_strands(string1, string2)
    string1.chars.zip(string2.chars)
  end
end

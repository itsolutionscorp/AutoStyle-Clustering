module Hamming
  def self.compute(original_strand, strand2)
    zip(original_strand, strand2).inject(0) do |distance, (base1, base2)|
      distance + calculate_distance(base1, base2)
    end
  end

  private

  def self.calculate_distance(base1, base2)
    if base1 && base2
      base1 == base2 ? 0 : 1
    else
      0
    end
  end

  def self.zip(string1, string2)
    string1.chars.zip(string2.chars)
  end
end

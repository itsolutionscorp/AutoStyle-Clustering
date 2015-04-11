class Hamming

  def self.compute(dna1, dna2)
    mistakes = 0

    dna1, dna2 = make_equal_length(dna1, dna2)

    dna1.chars.each_with_index do |x, index|
      mistakes += 1 if x != dna2[index]
    end

    mistakes
  end

  private

  def self.make_equal_length(string1, string2)
    string2 = string2[0, string1.length]
    string1 = string1[0, string2.length]

    return string1, string2
  end

end

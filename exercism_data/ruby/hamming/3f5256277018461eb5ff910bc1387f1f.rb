class Hamming

  def self.compute(dna1, dna2)
    if dna1.length <= dna2.length
      difference_counter(dna1, dna2)
    else
      difference_counter(dna2, dna1)
    end
  end

  def self.difference_counter(longer_string, shorter_string)
    difference = 0
    longer_string.split('').each_with_index do |character, index|
      if character != shorter_string[index]
        difference += 1
      end
    end
    difference
  end
end

class Hamming
  def self.compute(base_strand, strand_to_compare)
    hamming_number = 0
    base_strand.split('').each_with_index do |char, index|
      if strand_to_compare[index] != char
        hamming_number += 1
      end
    end
    hamming_number
  end
end

class Hamming
  def compute(strand_one, strand_two)
    zipped_strand_chars = strand_one.chars.zip(strand_two.chars)
    hamming_number = 0

    zipped_strand_chars.each do |a, b|
      unless a == b
        hamming_number += 1
      end
    end

    hamming_number
  end
end

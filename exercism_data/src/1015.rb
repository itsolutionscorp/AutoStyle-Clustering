class Hamming
  def compute(original_dna, copied_dna)
    diff_accumulator = 0

    original_dna.each_char.with_index do |nucleotide, index|
      copied_nucleotide = copied_dna[index]

      if copied_nucleotide && copied_nucleotide != nucleotide
        diff_accumulator += 1
      end
    end

    diff_accumulator
  end
end

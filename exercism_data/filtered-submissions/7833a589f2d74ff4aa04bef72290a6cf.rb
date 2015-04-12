class Hamming

  def compute(dna_1, dna_2)
    result = 0
    dna_1_sequence = dna_1.split('')
    dna_2_sequence = dna_2.split('')

    size = [dna_1_sequence.size, dna_2_sequence.size].min
    0.upto(size - 1) { |index| result += 1 unless dna_1_sequence[index] == dna_2_sequence[index] }
    result
  end

end

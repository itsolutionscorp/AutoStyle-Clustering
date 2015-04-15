# Differences between two homologous DNA strands
class Hamming
  def self.compute(dna1, dna2)
    dna_length = compare_dna_lengths(dna1.length, dna2.length)
    if dna_length == true
      hamming_distance = 0
      dna1.split('').each_with_index do |char, index|
        hamming_distance += 1 if char != dna2[index]
      end
      hamming_distance
    else
      dna_length
    end
  end

  def self.compare_dna_lengths(dna_1_length, dna_2_length)
    if dna_1_length == dna_2_length
      true
    elsif dna_1_length > dna_2_length
      1
    else
      2
    end
  end
end

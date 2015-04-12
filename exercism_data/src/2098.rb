class Hamming
  def compute(strand1, strand2)
    hamming_distance = 0
	strand1_nucleotides = strand1.split(//)
	strand2_nucleotides = strand2.split(//)

    strand1_nucleotides.length.times do |nucleotide|
      hamming_distance += 1 unless strand1_nucleotides[nucleotide] == strand2_nucleotides[nucleotide]
    end

	hamming_distance
  end
end

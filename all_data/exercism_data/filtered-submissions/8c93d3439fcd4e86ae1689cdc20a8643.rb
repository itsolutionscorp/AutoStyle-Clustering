def compute(dna_1, dna_2)
    dna_1.length >= dna_2.length ? min = dna_2.length : min = dna_1.length
    difference = 0
    min.times do |position|
      difference += 1 unless dna_1[position] == dna_2[position]
    end
    difference
  end
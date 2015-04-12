def compute dna_a, dna_b
    differences = 0
    index = 0

    loop do
      break if dna_a[index].nil? || dna_b[index].nil?
      differences += 1 unless dna_a[index] == dna_b[index]
      index += 1
    end

    differences
  end
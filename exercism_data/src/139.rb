def compute(strand_a, strand_b)
    distance = 0
    nucleotides_a = strand_a.split("")
    nucleotides_b = strand_b.split("")

    nucleotides_a.each_with_index do |nucleotide_a, i|
      distance += 1 unless nucleotide_a == nucleotides_b[i]
    end

    distance
  end
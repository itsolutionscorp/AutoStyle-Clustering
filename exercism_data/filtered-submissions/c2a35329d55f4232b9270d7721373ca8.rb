def compute dna_a, dna_b
    dna_a = dna_a.split ''
    dna_b = dna_b.split ''

    counter = 0
    pairs = dna_a.zip dna_b
    pairs.each do |a, b|
      break if !(a && b)
      counter += 1 if a != b
    end
    counter
  end
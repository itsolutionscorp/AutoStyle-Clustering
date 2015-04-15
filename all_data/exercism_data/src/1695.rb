def compute(dna_string_1, dna_string_2)
    dna_chars_1 = dna_string_1.split('')
    dna_chars_2 = dna_string_2.split('')
    total = 0

    dna_chars_1.each_with_index do |dna_1, index|
      total += 1 unless dna_1 == dna_chars_2[index]
    end
    
    total
  end
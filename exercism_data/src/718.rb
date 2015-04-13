def compute(first_dna_strand, second_dna_strand)
    number_of_differences = 0

    first_dna_strand.chars.each_with_index do |letter, index|
      number_of_differences += 1 unless letter == second_dna_strand[index]
    end

    number_of_differences
  end
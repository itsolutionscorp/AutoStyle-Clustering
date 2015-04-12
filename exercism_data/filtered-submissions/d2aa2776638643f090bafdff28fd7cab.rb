def compute(first_strand, second_strand)
        difference_counter = 0
        first_strand.each_char.with_index do |character, index|
            difference_counter += 1 unless character == second_strand[index]
        end
        difference_counter
    end
class Hamming
    def self.compare_length(first_to_compare, second_to_compare)
        if first_to_compare.length >= second_to_compare.length
            @@nucleic_array_length = first_to_compare.length
            @@length_difference = first_to_compare.length - second_to_compare.length
        else
            @@nucleic_array_length = second_to_compare.length
            @@length_difference = second_to_compare.length - first_to_compare.length
        end
    end

    def self.compute(nucleic_string_1, nucleic_string_2)
        0 if (nucleic_string_1 == nucleic_string_2)
        num_of_differences = 0
        nucleic_string_1_array = nucleic_string_1.split('')
        nucleic_string_2_array = nucleic_string_2.split('')

        compare_length(nucleic_string_1, nucleic_string_2)

        @@nucleic_array_length.times do |index|
            num_of_differences += 0 if nucleic_string_1_array[index] == nucleic_string_2_array[index]
            num_of_differences += 1 if nucleic_string_1_array[index] != nucleic_string_2_array[index]
        end

        num_of_differences - @@length_difference
    end
end

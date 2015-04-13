def compute(string_one, string_two)
    zipped_strings = string_one.chars.zip(string_two.chars)
    hamming_number = 0

    zipped_strings.each do |a, b|
      unless a == b
        hamming_number += 1
      end
    end

    hamming_number
  end
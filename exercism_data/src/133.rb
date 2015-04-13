def compute(first_string, second_string)
    hamming_count = 0
    first_string.split("").each_with_index do |char,i|
      hamming_count += 1 if second_string[i] != char
    end
    hamming_count
  end
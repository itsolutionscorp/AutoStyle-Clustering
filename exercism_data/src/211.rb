def compute(string1, string2)
    hamming_distance = 0
    string1.split(//).each_with_index do |char, index|
      hamming_distance += 1 unless char == string2[index]
    end
    hamming_distance
  end
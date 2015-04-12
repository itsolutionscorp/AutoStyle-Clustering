def compute(str1, str2)
    hamming_distance = 0
    return 0 if str1.nil? | str2.nil?
    0.upto([str1.length, str2.length].min - 1) do |index|
      hamming_distance += 1 if str1[index] != str2[index]
    end
    hamming_distance
  end
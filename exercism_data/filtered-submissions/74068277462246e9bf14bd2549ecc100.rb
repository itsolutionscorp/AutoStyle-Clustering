def compute(string1, string2)
    difference = 0
    string1.chars.each_with_index do |char, index|
      break if string2[index] == nil
      difference += 1 unless string2[index] == char
    end
    difference
  end
end
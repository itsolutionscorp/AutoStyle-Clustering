def compute (string1, string2)
    st1 = string1.chars.to_a
    st2 = string2.chars.to_a
    distance = 0
    st1.each_with_index do |string, index|
      distance += 1 if st2[index] && string != st2[index]
    end
    distance
  end
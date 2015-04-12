def compute (string1, string2)
    st1 = string1.chars #returns an array of characters, could also use string1.split(//)
    st2 = string2.chars
    distance = 0
    st1.each_with_index do |string, index|
      distance += 1 if st2[index] && string != st2[index] #increase distance counter if the equivalent index of the second string exists and doesn't match
    end
    distance
  end
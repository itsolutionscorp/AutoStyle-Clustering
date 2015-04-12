def compute s1, s2
    s1.each_char.take(s2.length).each_with_index.reject do |letter, index|
      s2[index] == letter
    end.length
  end
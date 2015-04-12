def compute s1, s2
    s1 =  s1.split('')
    s2 =  s2.split('')
    matches = s1.each_with_index.map {|letter, index| letter == s2[index]}
    matches.select {|m| m == false}.size
  end
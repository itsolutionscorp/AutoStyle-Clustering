def compute(string1, string2)
    max = [ string1.size, string2.size ].min
    
    distance = (0...max).count { |i| string1[i] != string2[i] }
  end
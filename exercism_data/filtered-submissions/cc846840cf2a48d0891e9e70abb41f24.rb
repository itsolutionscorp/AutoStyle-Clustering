def compute(string1, string2)
    raise "Strings must be same size" if string1.length != string2.length
    string1.chars.each_with_index.count do |char, index|
      char != string2[index]
    end
  end
def compute(string1, string2)
    string1.chars.zip(string2.chars).count do |element1, element2|
      element1 != element2
    end
  end
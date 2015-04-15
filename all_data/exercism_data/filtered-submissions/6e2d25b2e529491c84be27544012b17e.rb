def compute(string1, string2)
    string1.chars.zip(string2.chars).inject(0) { |sum, elements| sum += elements.first.casecmp(elements.last).abs }
  end
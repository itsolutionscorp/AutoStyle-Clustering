def compute(strain1, strain2)
    strain1.chars.zip(strain2.chars).inject(0) { |distance, elements|
      (elements[0] == elements[1]) ? distance : distance + 1
    }
  end
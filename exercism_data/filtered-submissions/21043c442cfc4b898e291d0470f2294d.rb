def compute(a,b)



    if a.length < b.length
      shortest_nucleic_acid = a
      longest_nucleic_acid = b
    else
      shortest_nucleic_acid = b
      longest_nucleic_acid = a
    end
    hamming_distance = 0
    longest_nucleic_acid = longest_nucleic_acid.split(//)
    shortest_nucleic_acid.each_char.with_index do |char, index|
      hamming_distance += 1 if longest_nucleic_acid[index] != char
    end
    hamming_distance
  end
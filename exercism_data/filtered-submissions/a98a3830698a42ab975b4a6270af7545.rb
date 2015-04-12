class Hamming
  def compute(a,b)
    # we Must ignore any extra nucleotides in a longer nucleic_acid
    # so first we find which nucleic_acid is the shortest setting
    # nucleic_acid a to shortest if they are equal.
    if a.length > b.length
      shortest_nucleic_acid = b
      longest_nucleic_acid = a
    else 
      shortest_nucleic_acid = a
      longest_nucleic_acid = b
    end
    hamming_distance = 0
    longest_nucleic_acid = longest_nucleic_acid.split(//)
    shortest_nucleic_acid.each_char.with_index do |char, index|
      hamming_distance += 1 if longest_nucleic_acid[index] != char
    end
    hamming_distance
  end
end

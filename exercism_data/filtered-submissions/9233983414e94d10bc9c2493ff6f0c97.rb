def compute(strand_one, strand_two)
    hamming_distance = 0

    strand_one = strand_one.split(//)
    strand_two = strand_two.split(//)

    strand_one.each_with_index do |protein, index|
      unless protein.nil? || strand_two[index].nil?
        hamming_distance += 1 if protein != strand_two[index]
      end
    end

    return hamming_distance
  end
def compute(strand_one, strand_two)
    point_mutations = 0

    strand_one.split(//).each_with_index do |character, index|
      break if strand_two.slice(index).nil?
      (point_mutations += 1) if strand_two.slice(index) != character
    end

    point_mutations

  end
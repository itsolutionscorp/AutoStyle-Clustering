def compute(strand_one, strand_two)
    difference = 0

    strand_one.length.times do |index|
      if strand_one[index].nil? or strand_two[index].nil?
        return difference
      elsif strand_one[index] != strand_two[index]
        difference += 1
      end
    end

    return difference
  end
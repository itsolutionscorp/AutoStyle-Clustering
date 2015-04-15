def compute(strand_one, strand_two)
    strand_one.split(//).each_with_index.map do |base, index|
      if base == strand_two[index] || index >= strand_two.length
        0
      else
        1
      end
    end.reduce(:+)
  end
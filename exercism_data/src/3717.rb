def compute(strand_left, strand_right)
    strand_left_list = strand_left.chars
    strand_right_list = strand_right.chars

    differences = 0
    strand_left_list.each_index do |i|
      if strand_left_list[i] != strand_right_list[i]
        differences = differences + 1
      end
    end
    differences
  end
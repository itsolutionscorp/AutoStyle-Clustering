def compute( strand_one, strand_two )
    diff = 0
    strand_diff = strand_one.chars.each_with_index do |i, count|
      ( diff += 1 ) if strand_one[count] != strand_two[count] && strand_two[count]
    end
    diff
  end
def compute(first, second)
    zipped_point_pairs = first[0..second.length - 1].chars.zip(second.chars)
    zipped_point_pairs.inject(0) do |distance, (first_point, second_point)|
      distance = distance + 1 unless first_point == second_point
      distance
    end
  end
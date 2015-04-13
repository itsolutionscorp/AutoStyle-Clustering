def compute( a, b )
    hamming_distance = 0

    sorted_by_length = [a,b].sort_by( &:length )

    sorted_by_length[0].chars.each_with_index do |char, index|
      if char != sorted_by_length[1][index]
        hamming_distance += 1
      end
    end

    hamming_distance
  end
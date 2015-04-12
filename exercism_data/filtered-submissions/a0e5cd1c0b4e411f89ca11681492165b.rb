def compute(left_strand,right_strand)
    return 0 if left_strand == right_strand

    left_chars = left_strand.chars
    right_chars = right_strand.chars

    distance = 0

    left_chars.each_with_index do |left_char,idx|
      right_char = right_chars[idx]
      break if right_char.nil? # ignore extra length on first strand when longer

      distance += 1 unless left_char == right_char
    end

    distance
  end
end
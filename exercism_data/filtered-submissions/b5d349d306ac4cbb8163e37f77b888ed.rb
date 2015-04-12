def compute(lhs, rhs)
    difference = 0
    rhs.chars.each_with_index do |ch, idx|
      difference += 1 unless lhs[idx].nil? || lhs[idx] == ch
    end
    difference
  end
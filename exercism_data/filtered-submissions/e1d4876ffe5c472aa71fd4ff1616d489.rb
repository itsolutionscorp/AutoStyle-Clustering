def compute(a,b)
    raise 'The arguments do not have the same length.' unless a.length == b.length

    b_chars = b.chars
    distance = 0

    a.chars.each_with_index {|c,i| distance += 1 unless c == b_chars[i] }

    return distance
  end
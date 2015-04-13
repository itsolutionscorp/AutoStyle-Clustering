def compute(a, b)

      return 0 if a == b


      a_chars, b_chars = a.chars, b.chars


      if a_chars.count > b_chars.count
        a_chars = a_chars.first(b_chars.count)
      elsif a_chars.count < b_chars.count
        b_chars = b_chars.first(a_chars.count)
      end
      strands_length = a_chars.count


      distance = 0
      (0..strands_length).each {|i| distance += 1 if a_chars[i] != b_chars[i] }
      return distance
    end
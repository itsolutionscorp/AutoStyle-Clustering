def compute(a, b)

      return 0 if a == b


      a_chars, b_chars = a.chars, b.chars


      if a_chars.count > b_chars.count
        a_chars = a_chars.first(b_chars.count)
      elsif a_chars.count < b_chars.count
        b_chars = b_chars.first(a_chars.count)
      end


      evaluation = a_chars.map.with_index do |c,i|
        c == b_chars[i] ? 0 : 1
      end
      evaluation.inject(&:+)
    end
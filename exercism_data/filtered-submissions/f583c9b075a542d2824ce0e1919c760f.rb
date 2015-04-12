def compute(a, b)
      # there is no hamming distance between equal strands
      return 0 if a == b

      # pull characters of each strand into arrays
      a_chars, b_chars = a.chars, b.chars

      # ensure strand lengths are equal
      if a_chars.count > b_chars.count
        a_chars = a_chars.first(b_chars.count)
      elsif a_chars.count < b_chars.count
        b_chars = b_chars.first(a_chars.count)
      end

      # calculate and return distance
      evaluation = a_chars.map.with_index do |c,i|
        c == b_chars[i] ? 0 : 1
      end
      evaluation.inject(&:+) # sums the elements of evaluation (0s or 1s)
    end

  end
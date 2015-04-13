def compute(left, right)
    count = 0

    left.split("").zip(right.split("")).each do |l_char, r_char|
      break unless l_char && r_char

      count += 1 if l_char != r_char
    end

    count
  end
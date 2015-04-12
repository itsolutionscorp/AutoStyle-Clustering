def compute(a, b)
    a_chars = a.chars
    b_chars = b.chars

    a_chars.each_with_index.inject(0) do |distance, (char, index)|
      if char != b_chars[index]
        distance += 1
      end

      distance
    end
  end
end
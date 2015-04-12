def compute(a, b)
    total = 0

    a.chars.each_with_index do |a_chr, index|
      total += 1 if a_chr != b[index]
    end

    return total
  end
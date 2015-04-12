def compute(strand_a, strand_b)
    strand_a_chars = strand_a.chars
    strand_b_chars = strand_b.chars
    count = 0
    strand_a_chars.each_with_index do |char, index|
      if char != strand_b_chars[index]
        count += 1
      end
    end
    count
  end
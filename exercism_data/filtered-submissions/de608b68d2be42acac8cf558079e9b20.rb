def compute(seq1, seq2)
    count = 0
    seq1.chars.each_with_index do |elem, index|
      count += 1 unless elem.eql?(seq2.chars[index])
    end
    count
  end
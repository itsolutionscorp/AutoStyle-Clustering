class Hamming
  def compute (seq1, seq2)
    seq1.strip!
    seq2.strip!
    count = 0
    i = 0
    len = [seq1.length, seq2.length].min
    while i < len do
      if seq1[i] != seq2[i]
        count += 1
      end
      i += 1
    end

    return count
  end
end

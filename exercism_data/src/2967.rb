class Hamming
  def compute(seq1, seq2)
    num_elm = [seq1.size, seq2.size].min
    (0...num_elm).reduce(0) { |total, value|
      seq1[value] != seq2[value] ? total+1 : total
    }
  end
end

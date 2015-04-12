class Hamming
  def compute(seq1, seq2)
    0 if seq1 == seq2
    index = 0
    count = 0
    shorter_seq = seq1.length <= seq2.length ? seq1 : seq2
    if seq1.length <= seq2.length
      shorter_seq = seq1
      longer_seq = seq2
    else
      shorter_seq = seq2
      longer_seq = seq1
    end
    shorter_seq.each_char do |base|
      count += 1 if base != longer_seq[index]
      index += 1
    end
    count
  end
end

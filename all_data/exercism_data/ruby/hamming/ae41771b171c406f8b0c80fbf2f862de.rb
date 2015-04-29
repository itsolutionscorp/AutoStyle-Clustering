class Hamming
  def self.compute(seq1, seq2)
    longer_seq = seq1.size >= seq2.size ? seq1 : seq2
    shorter_seq = longer_seq == seq1 ? seq2 : seq1
    index = 0
    count = 0
    shorter_seq.each_char do |base|
      count += 1 if base != longer_seq[index]
      index += 1
    end
    count
  end
end

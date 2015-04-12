class Hamming
  def compute(seq1, seq2)
    seq1 = seq1.split('')
    seq2 = seq2.split('')
    seq1.zip(seq2).count do |nucleotide1, nucleotide2|
      nucleotide1 != nucleotide2
    end
  end
end

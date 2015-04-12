class Hamming
  def compute(seq1, seq2)
    seq1.chars.zip(seq2.chars).count do |nucleotide1, nucleotide2|
      nucleotide1 != nucleotide2
    end
  end
end

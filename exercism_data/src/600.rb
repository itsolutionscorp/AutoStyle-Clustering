def compute(seq1, seq2)
    seq1 = seq1.split('')
    seq2 = seq2.split('')
    distance = 0
    seq1.zip(seq2).each do |nucleotide1, nucleotide2|
      distance += 1 if nucleotide1 != nucleotide2
    end
    distance
  end
class Hamming
  def compute (nucleotide1, nucleotide2)
    i = 0
    hammingCount = 0

    baseString = nucleotide1.length <= nucleotide2.length ? nucleotide1 : nucleotide2 

    while i < baseString.length
      if nucleotide1[i] != nucleotide2[i]
        hammingCount += 1
      end
      i += 1
    end
    hammingCount
  end
end

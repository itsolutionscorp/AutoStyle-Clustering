def compute(seq1,seq2)
    h=0
    unless seq1.length != seq2.length
      @seq1 = seq1.split('')
      @seq2 = seq2.split('')
      @seq1.zip(@seq2).each do |gen1,gen2|
        h += 1 if gen1 != gen2
      end
    end
    h
  end
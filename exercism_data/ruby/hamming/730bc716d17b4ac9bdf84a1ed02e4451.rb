class Hamming
  def self.compute(sq1, sq2)
    sq1 = sq1.split(//)
    sq2 = sq2.split(//)
    sqllength = sq1.length
    sqllength = sq2.length if sq2.length < sq1.length
    i = 0
    hammcounter = 0

    while i < sqllength
      if sq1[i] != sq2[i]
        hammcounter += 1
      end
      i += 1
    end
    hammcounter
  end
end

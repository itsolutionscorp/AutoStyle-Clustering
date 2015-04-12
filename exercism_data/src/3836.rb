def compute(mydna, yourdna)
    sequence = mydna.chars
    altsequence =  yourdna.chars
    combinedsequence = sequence.zip(altsequence)
    differencecount = 0
    combinedsequence.each{|x,y|
      if (x == y)
        differencecount = differencecount + 1
      else
        differencecount = differencecount + 0
      end
    }

    return differencecount
  end
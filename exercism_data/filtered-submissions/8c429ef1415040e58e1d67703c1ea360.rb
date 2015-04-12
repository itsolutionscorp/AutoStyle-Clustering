def compute(mydna, yourdna)
    combinedsequence = mydna.chars.zip(yourdna.chars)
    differencecount = 0
    combinedsequence.each{|x,y|
      if (x != y) && !(x.nil?) && !(y.nil?)
        differencecount = differencecount + 1
      end
    }

    differencecount
  end
def compute(strandOne, strandTwo)

    d = 0

    i = 0

    strandOne.split('').each do |s1|
      if s1 != strandTwo[i]
        d += 1
      end
      i += 1
    end

    d

  end
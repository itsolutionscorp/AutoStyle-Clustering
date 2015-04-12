def compute(oldStrand, newStrand)
        oldStrand = oldStrand[0, newStrand.size]
        oldStrand.chars.zip(newStrand.chars).count{|x,y| x != y}
    end
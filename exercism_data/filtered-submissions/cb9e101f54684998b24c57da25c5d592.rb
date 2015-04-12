def compute (strand1, strand2)
    length = [strand1.length, strand2.length].min
    diff = 0
    
    length.times do |i|
      char1 = strand1[i].chr
      char2 = strand2[i].chr
      if char1.casecmp(char2) != 0
        diff += 1
      end
    end

    diff
  end
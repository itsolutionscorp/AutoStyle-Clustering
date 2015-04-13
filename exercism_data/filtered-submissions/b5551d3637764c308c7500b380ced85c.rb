def compute(strand1, strand2)

      if strand1.length > strand2.length
        strand1 = strand1[0..strand2.length-1]
      end

      if strand2.length > strand1.length
        strand2 = strand2[0..strand1.length-1]
      end


      strand1 = strand1.split(//)
      strand2 = strand2.split(//)


      cnt = 0
      strand1.zip(strand2).each do |strand1, strand2|
        cnt += 1 if strand1 != strand2
      end
      cnt
    end
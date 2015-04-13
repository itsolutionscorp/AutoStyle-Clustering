def compute(strand1, strand2)

    h, i = 0, 0


    strand1 = strand1.split(//)
    strand2 = strand2.split(//)


    if strand1.length > strand2.length
      strand1 = strand1.slice(0..strand2.length-1)
    elsif strand2.length > strand1.length
      strand2 = strand2.slice(0..strand1.length-1)
    end



    strand1.each do |x|
      if x != strand2[i]
        h += 1
      end
      i += 1
    end


    return h
  end
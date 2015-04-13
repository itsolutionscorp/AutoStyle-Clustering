def compute(strand1, strand2)

    if strand1.length > strand2.length
      strand1 = strand1[0...strand2.length]
    elsif strand1.length < strand2.length
      strand2 = strand2[0...strand1.length]
    end


    bananas = 0
    strand1.chars.each_with_index do |nuke, index|
      if nuke != strand2[index]
        bananas += 1
      end
    end
    bananas
  end
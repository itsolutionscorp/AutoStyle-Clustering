def compute(strand1, strand2)


    if strand1 === strand2
      0
    end

    diff = 0



    strand1.each_char.with_index do |character, index|
      if character != strand2[index]
        diff += 1
      end
    end

    diff
  end
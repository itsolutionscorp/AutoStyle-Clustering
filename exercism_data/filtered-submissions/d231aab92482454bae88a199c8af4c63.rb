def compute (strand1, strand2)
    @strand1 = strand1.chars
    @strand2 = strand2.chars

    distance = 0


    if @strand1.length > @strand2.length
      @strand1 = @strand1[0,strand2.length]
    elsif @strand2.length > @strand1.length
      @strand2 = @strand2[0,strand2.length]
    end


    @strand1.each_with_index do |strand1_char, index|
      @strand2_char = strand2[index]
        if @strand2_char != strand1_char
          distance += 1
      end
    end

    distance

  end
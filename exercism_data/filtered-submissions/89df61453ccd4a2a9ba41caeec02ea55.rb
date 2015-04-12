def compute(strand1, strand2)
    @counter = 0
    @length = (strand1.length > strand2.length ? strand2.length : strand1.length) - 1
    (0..@length).each do |i|
       if strand1[i] != strand2[i]
         @counter += 1
       end
    end
    return @counter
  end
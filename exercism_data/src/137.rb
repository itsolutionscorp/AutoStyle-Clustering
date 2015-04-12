def compute(strand1, strand2)
    strand1 = strand1.chars
    strand2 = strand2.chars
    counter = 0

    zipped = strand1.zip(strand2)

    zipped.each do |x,y|
      if x == nil || y == nil
        nil
      elsif [x] != [y]
        counter += 1
      else
        counter
      end
    end
    counter
  end
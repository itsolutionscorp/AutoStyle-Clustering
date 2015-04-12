def compute(strand1, strand2)
    length = [strand1.length, strand2.length].min

    (0...length).count do |n|
      strand1[n] != strand2[n]
    end
  end
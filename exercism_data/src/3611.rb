def compute(strand1, strand2)
    difference = 0
    length = [strand1.length, strand2.length].min
    length.times do |count|
      difference += 1 if strand1[count] != strand2[count]
    end
    difference
  end
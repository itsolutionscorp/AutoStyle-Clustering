def compute(strand1, strand2)
    length = [strand1.length, strand2.length].min
    distance = 0
    (0..length-1).each do |index|
      distance += 1 if strand1[index] != strand2[index]
    end
    distance
  end
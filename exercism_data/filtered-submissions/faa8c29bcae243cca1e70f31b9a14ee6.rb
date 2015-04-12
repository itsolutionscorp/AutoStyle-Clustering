def compute(strand1, strand2)
    strand1.length < strand2.length ? comp = strand1.length : comp = strand2.length
    counter = 0
    comp.times do |i|
      counter += 1 unless strand1[i] == strand2[i]
    end
    counter
  end
def compute(strand_1, strand_2)
  	raise ArgumentError if strand_1.length != strand_2.length

  	distance = 0
    strand_1.length.times do
      distance += 1 if letter != strand_2[index]
    end
    distance
  end
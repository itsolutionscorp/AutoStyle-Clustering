def compute(strand_one, strand_two)
    n = [strand_one.length,  strand_two.length].min
    counter = 0
    n.times do |i|
      counter += 1 unless strand_one[i] == strand_two[i]
    end
    counter
  end
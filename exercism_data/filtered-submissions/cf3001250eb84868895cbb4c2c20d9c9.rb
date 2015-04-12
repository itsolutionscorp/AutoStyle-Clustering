def compute(strand1, strand2)
    (0...strand1.size).reduce(0) do |acc, i|
      strand1[i] == strand2[i] ? acc : acc + 1
    end
  end
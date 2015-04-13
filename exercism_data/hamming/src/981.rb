def compute(strand1, strand2)
    strand1.split(//).zip(strand2.split(//)).reduce(0) do |distance, bases|
      distance += (bases[0] <=> bases[1]).abs
    end
  end
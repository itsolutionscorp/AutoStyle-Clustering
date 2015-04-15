class Hamming

  def self.compute(x,y)
    # if x == y
    #   0
    # end
    if x.length < y.length
      length = x.length
    else
      length = y.length
    end
    count = 0
    (0..length-1).each do |i|
      if x[i] != y[i]
        puts count += 1
      end
    end
    count
  end
end

# Hamming.compute('AG','AT')

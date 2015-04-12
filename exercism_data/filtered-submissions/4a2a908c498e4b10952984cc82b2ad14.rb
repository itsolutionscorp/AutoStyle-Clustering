class Hamming

  def compute(x,y)
    mutations = 0

    #find the shorter strand to determine how many iterations
    if x.length != y.length && (x.length < y.length)
      length = x.length
    else
      length = y.length
    end

    #iterate over both strands and count the number of mutations
    length.times do |i|
      if x[i] != y[i]
        mutations += 1
      end
    end

    #return value
    mutations
  end

end

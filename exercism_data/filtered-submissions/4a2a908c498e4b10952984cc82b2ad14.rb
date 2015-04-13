def compute(x,y)
    mutations = 0


    if x.length != y.length && (x.length < y.length)
      length = x.length
    else
      length = y.length
    end


    length.times do |i|
      if x[i] != y[i]
        mutations += 1
      end
    end


    mutations
  end
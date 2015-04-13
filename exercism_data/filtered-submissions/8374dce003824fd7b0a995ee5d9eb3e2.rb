def compute(a, b)
    x = a.upcase.split(//)
    y = b.upcase.split(//)
    sum = 0
    for i in 0...x.length do
      if(y.length() >= i+1)
        if(x[i] != y[i])
          sum += 1
        end
      end
    end
    return sum
  end
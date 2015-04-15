def compute(x,y)
    zipped = x.split('').zip(y.split(''))
    zipped.reduce(0) do |acc, pair|
      x, y = pair
      x == y ? acc : acc + 1
    end
  end
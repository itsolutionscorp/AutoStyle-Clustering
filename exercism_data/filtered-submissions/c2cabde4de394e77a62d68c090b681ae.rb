def compute(x, y)
    @count = 0

    @limit = x.length < y.length ? x.length : y.length

    (0...@limit).each do |i|
      if x[i] != y[i]
        @count += 1
      end
    end

    return @count

  end
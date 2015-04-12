def compute(x, y)
    count = 0
    first = x.split(//)
    second = y.split(//)

    size = x.length > y.length ? y.length : x.length

    size.times do |i|
      if first[i] != second[i]
        count += 1
      end
    end

    count
  end
def compute(one, two)
    count = 0

    length = [one.length, two.length].min

    length.times do |i|
      count += 1 unless one[i] == two[i]
    end

    count
  end
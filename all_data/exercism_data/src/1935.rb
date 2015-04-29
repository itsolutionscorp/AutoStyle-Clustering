def compute a, b
    length = [a.length, b.length].min

    distance = 0

    (0...length).map do |n|
      if a[n] != b[n]
        distance += 1
      end
    end

    distance
  end
def compute a, b
    length = [a.size, b.size].min

    (0...length).count do |i|
      a[i] != b[i]
    end
  end
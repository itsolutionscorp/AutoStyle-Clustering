def compute(a, b)
    a = a.scan(/./)
    b = b.scan(/./)
    diffs = 0

    min_length = [a.length, b.length].min

    min_length.times do |index|
      if a[index] != b[index]
        diffs += 1
      end
    end

    diffs
  end
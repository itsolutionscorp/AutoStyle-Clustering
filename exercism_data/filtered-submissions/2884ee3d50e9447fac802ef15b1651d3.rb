def compute(a, b)
    diffs      = 0
    min_length = [a.length, b.length].min

    min_length.times do |index|
      diffs += 1 if a[index] != b[index]
    end

    diffs
  end
end
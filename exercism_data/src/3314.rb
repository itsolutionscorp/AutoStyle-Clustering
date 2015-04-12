def compute(original, replicated)
    difference = 0

    (0..[original.length, replicated.length].min-1).each do |i|
      difference += 1 unless original[i] == replicated[i]
    end

    difference
  end
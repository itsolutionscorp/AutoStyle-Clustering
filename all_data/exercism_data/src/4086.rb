def compute(original, replicated)
    difference = 0

    original.split('').each_with_index do |v, i|
      difference += 1 if replicated[i] unless v == replicated[i]
    end

    difference
  end
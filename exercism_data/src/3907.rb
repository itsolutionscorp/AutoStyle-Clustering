def compute(a, b)
    hamming = 0

    a.split('').each_with_index do |item, i|
      hamming += 1 if b[i] != item
    end

    hamming
  end
def compute(original, current)
    o = original.split ''
    c = current.split ''
    hamming_counter = 0
    o.each_with_index do |letter, index|
      if letter != c[index]
        hamming_counter += 1
      end
    end
    hamming_counter
  end
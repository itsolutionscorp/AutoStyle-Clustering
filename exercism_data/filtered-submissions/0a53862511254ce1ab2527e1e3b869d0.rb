def compute(first, second)
    first = first.chars
    second = second.chars
    index = 0
    hamming_count = 0

    while index < first.count && index < second.count do
      if first[index] == second[index]
        index += 1
      else
        hamming_count += 1
        index += 1
      end
    end
    hamming_count
  end
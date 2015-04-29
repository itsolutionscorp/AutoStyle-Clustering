def compute(original, copy)
    count = 0

    original.chars.each_with_index do |strand, idx|
      break if idx >= copy.size
      count += 1 if strand != copy[idx]
    end

    return count
  end
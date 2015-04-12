def compute(first, second)
    dist = 0
    first.chars.each_index do |i|
      dist += first[i] == second[i] ? 0 : 1
    end
    return dist
  end
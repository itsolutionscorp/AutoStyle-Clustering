def compute(first, second)
      distance = 0
      (0...first.length).each do |i|
        break if i == second.length
        distance += 1 if first[i] != second[i]
      end
      distance
    end
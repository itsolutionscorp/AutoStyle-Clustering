def compute first, second
    first.split("").reduce(0) do |distance, an|
      distance += 1 unless an == second[0]
      second[0] = ''
      return distance if second.empty?
      distance
    end
  end
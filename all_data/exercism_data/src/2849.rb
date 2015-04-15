def compute(first, second)
    results = 0
    (0...[first.size, second.size].min).each do |i|
      if first[i] != second[i] then results += 1 end
    end
    return results
  end
end
def compute(first, second)
    return 0 if first == second

    hamming = 0
    [first.length, second.length].min.times do |i|
      hamming += first[i] == second[i] ? 0 : 1
    end
    hamming
  end
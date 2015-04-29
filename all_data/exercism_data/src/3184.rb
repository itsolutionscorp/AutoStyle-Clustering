def compute(first, second)
    if (first == second)
      return 0
    end
    
    length = first.length > second.length ? second.length : first.length
    hamming = 0
    length.times do |i| 
      if (first[i] != second[i])
        hamming += 1
      end
    end
    hamming
  end
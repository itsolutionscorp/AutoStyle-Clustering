def compute(first, second)
    differences = 0
    
    if first == second
      return differences
    end

    for index in (0..first.length - 1)
      if index + 1 > second.length
        return differences
      end
      
      unless first[index] == second[index]
        differences += 1
      end
      
      differences
    end
  end
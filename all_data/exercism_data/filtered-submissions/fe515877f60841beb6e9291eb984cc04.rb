def compute(first, second)
    distance = 0
    imax = first.length > second.length ? second.length : first.length
    for i in 0...imax
	 if first[i] != second[i] then distance +=1 end
    end
    return distance
  end
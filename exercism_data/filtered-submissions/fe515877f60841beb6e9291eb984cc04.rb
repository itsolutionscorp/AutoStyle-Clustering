class Hamming
  def compute(first, second)
    distance = 0
    imax = first.length > second.length ? second.length : first.length #we must do the number of times equal the length of the smallest strand
    for i in 0...imax
	 if first[i] != second[i] then distance +=1 end 
    end 
    return distance
  end
end

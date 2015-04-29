def compute(left, right)
		count = 0
		index = 0
		shortest_length = left.length < right.length ? left.length : right.length
    
    while(index < shortest_length) do
      different_letters = (left[index] != right[index])
			index += 1
      
      if different_letters
        count += 1
      end
			
		end
    
    return count
	end
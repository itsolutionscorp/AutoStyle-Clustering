class Hamming 

  def compute(ancestor, decendent) 
		ancestor.split('').each_with_index.inject(0) do |diff, (char, index)| 
			break diff unless decendent[index]
			char == decendent[index] ? diff : diff += 1 
		end	
	end
end

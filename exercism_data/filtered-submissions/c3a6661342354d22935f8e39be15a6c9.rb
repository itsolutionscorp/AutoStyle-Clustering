def compute(name1, name2)
		puts "name1: #{name1}"
		puts "name2: #{name2}"

		if name1.length == name2.length
			if name1 == name2
				return 0
			else
				c = 0
				name1.length.times {|i| if name1[i] != name2[i] then c+=1 end}
				return c
			end
		else 
			puts "The length of the DNA strands is not equivalent"	
		end
	end
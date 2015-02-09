
def is_anagram(x,y)
	return x.chars.sort.join.downcase() == y.chars.sort.join.downcase()
end


def combine_anagrams(words)

    groups = []
	#words.each do |w|
  	#	sorted.push(w.chars.sort.join)
  	#end
    words.each_index do |x|
    	g = Array.new
    	g.push(words[x])
    	words.each_index do |y|
    		unless x == y  
    		  if is_anagram(words[x],words[y])
    				g.push(words[y])
    		  end
    		end
    	end
    	unless groups.flatten.index(words[x]) 
    			groups.push(g)

    	end
    end 
    	#groups.push(sorted.find_all{|item| item =~ /^#{s}$/i})
  	return groups
end

#test = ['cars','A', 'a','a', 'cars', 'creams', 'for', 'potatoes', 'racs', 'four', 'SCAR', 'creams', 'scream']
#test = ['B',"foo","ofo","oOF"]
#print combine_anagrams(test)
def combine_anagrams(words)
	y=[]
	anagrams=[]
	words.each{|x| s=x.downcase.split(//).sort.join
	y<<s.downcase}	
	lookup=[]
	for i in 0..y.length-1
		group=[]
   		if(lookup.index(y[i])==nil)
   			lookup<<y[i]
   			group<<words[i]
   			for j in i+1..y.length	
   				if y[i] == y[j]
   					group<<words[j] 					
   				end
   			end
   		end 
   		if(group.length>0)
   			anagrams<<group
   		end
	end
	anagrams
end


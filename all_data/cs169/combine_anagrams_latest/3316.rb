def combine_anagrams(words)
	i=0;j=0
	k=0
	temp=Array.new
	words.each do |str|
		str=str.split('')
		str=str.sort_by{|x| x.downcase}
		str=str.join
		temp[i]=str
		i=i+1;
	end
	print temp
	final=Array.new
	pos=Array.new
	temp.each do |str|
		anagram=Array.new
		while j<temp.length
			if temp[j].downcase==str.downcase
				if pos.include?(j)!=true
					anagram.push(words[j])
					pos.push(j)
				end	
			end			
		j=j+1
		end
	if anagram.empty?!=true
		final.push(anagram)
	end
	j=0
	end
return final		
end


#words=STDIN.gets
#words=eval(words)
#combine_anagrams(words)

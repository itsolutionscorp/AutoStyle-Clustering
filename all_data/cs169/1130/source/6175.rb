def arr(str)
	a = []
	0.upto(str.length-1) do |i|
		a << str.downcase[i]
	end
	
	return a.sort
end

def combine_anagrams(words)
	final = []
	count=-1
	s=[]
	a=[]
	
	0.upto(words.length-1) do |i|
		a[i] = 0
		s[i] = arr(words[i])
	end
	
	0.upto(words.length-1) do |i|
		flag = 0
			
		0.upto(i-1) do |j|
			if(s[i] == s[j])
				final[a[j]] << words[i]
				a[i] = a[j]
				flag = 1
				break
			end
		end
		
		if flag == 0
			count += 1
			final[count] = [words[i]]
			a[i] = count
		end
	end
	return final
end
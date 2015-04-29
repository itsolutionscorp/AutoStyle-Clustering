def combine_anagrams(words)
result=[]
x=words.length-1
t=0
for t in 0..x do
	if !("*"==words[t]) then pomocna=[words[t]] end
		for i in t+1..x do
			if words[t].downcase.chars.sort==words[i].downcase.chars.sort
				if !("*"==words[t]) then pomocna<<words[i] end
				words[i]="*" 
			end
		end 
	unless (pomocna==[]) then result<<pomocna end
	pomocna=[]
end
return result
end

#puts combine_anagrams(['HeLLo', 'hello','hellO','a','marija','MarIja','konj','a'])

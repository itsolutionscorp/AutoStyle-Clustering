# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
 
def combine_anagrams(words)

	anagrams = Hash.new(0)
	words.each do |word|
        sword=word.downcase.chars.sort.join	
		if(anagrams[sword] == 0)
			anagrams[sword]=Array.new
			anagrams[sword].push(word)
		else
			anagrams[sword].push(word)			
		end
	end
	a=Array.new
	i=0
	anagrams.each do |k,vs| 
		a[i]=vs
		i+=1
	end
	return a
    
end


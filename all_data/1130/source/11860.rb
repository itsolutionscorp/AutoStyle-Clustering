
def alpha_sort(w)
	a=Array[];
	w.each_char() do | c |
		a.push(c);
	end
	a.sort!
	r='';
	a.each() do | c |
		r=r+c;
	end
	r
end

def combine_anagrams(words)
	h=Hash[]
	words.each() do | word |
		anagram_master=alpha_sort(word).downcase
		if(h[anagram_master].nil?)
			h[anagram_master]=Array[word]
		else
			if(h[anagram_master].index(word).nil?)
				h[anagram_master].push(word)
			end
		end
	end
	
	r=Array[]
	h.each() do | elem |
		r.push(elem[1]);
	end
	r;
end

# input: 
input=
['cArs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
puts combine_anagrams(input).inspect

puts combine_anagrams(['HeLLo', 'hello']).inspect
puts combine_anagrams(['hello', 'hello', 'hello', 'hello']).inspect


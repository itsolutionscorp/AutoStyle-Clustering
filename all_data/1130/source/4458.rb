def anagrams?(w1, w2)
  return w1.downcase.split('').sort == w2.downcase.split('').sort
end

def combine_anagrams(words)
	new_collect=[]
	while words.length>0
		current=words[0]
		collect=[]
		words.delete_if{ |x|
			if anagrams?(current,x)
				collect+=[x]
			end
		}
		new_collect+=[collect]
	end
return new_collect
end

#combine_anagrams(['cars','for','potatoes','racs','four','scar','creams','scream'])
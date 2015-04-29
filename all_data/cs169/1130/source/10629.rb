def combine_anagrams(words)
    result={}
    
    words.each do |a|
	
          	
               if result[a.downcase.chars.sort.join]==nil
		result[a.downcase.chars.sort.join]=[a]
               else
		result[a.downcase.chars.sort.join].push(a)
	
               end

    end

return result.values
    
end

#puts combine_anagrams(["scream","creams", "casr","hell", "cars","racs","jammu"])


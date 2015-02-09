def combine_anagrams(words)
  
  anagrams = Hash.new
  result = Array.new
  if (words.kind_of?(Array))
  	words.each{|word|
  		res = word.downcase.chars.sort { |a, b| a.casecmp(b) } .join
	
  	  if(anagrams[res])
    	  	anagrams[res] = anagrams[res] << word
    	else 
    	  anagrams[res] = Array[word]
      end
    }
  else
    return Array[words]
  end
  anagrams.each{ |k,v|
    result << v
  }
  return(result)
end

puts(combine_anagrams('a').to_s)
puts(combine_anagrams(['auto', 'voiture', 'radar']).to_s)
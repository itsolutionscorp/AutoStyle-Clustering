def combine_anagrams(words)
  temp=Array.new
  words.map{|i| 
    sorted_words= i.downcase.split(//).sort.join()
    temp.push(sorted_words)
    }
   result=Array.new
   for i in (0..words.length-1)
     result.push([])
    for j in (0..words.length-1)
      if(temp[i]==temp[j])
        result[i].push(words[j])
      end
    end
  end
  
  return result.uniq
end

input= ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 
'scream']

print combine_anagrams(input)

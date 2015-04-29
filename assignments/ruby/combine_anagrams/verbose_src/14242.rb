def combine_anagrams(words)

  if words.length > 0

    result = [[words[0]]]

    for i in 1 .. words.length-1
      added = 0
      for j in 0 .. result.length-1
        if words[i].downcase.chars.sort.join == result[j][0].downcase.chars.sort.join
          result[j] += [words[i]]
          added = 1
        end
      end
      if added == 0
        result += [[words[i]]]
      end
    end  
    
    result
  
  else
    result = []
  end  
end
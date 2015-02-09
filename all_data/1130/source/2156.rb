def are_anagrams?(str1, str2)
  return str1.downcase.chars.sort.join == str2.downcase.chars.sort.join
end

def combine_anagrams(words)
   result =  Array.new
   words.each do |w|
     flag = false
     result.each do |a|
       if are_anagrams?(a[0],w)
          a = a.concat([w])
         flag = true
       end
     end
     if !flag
       result =  result.concat(  [[w]])
     end
   end
   return result
end

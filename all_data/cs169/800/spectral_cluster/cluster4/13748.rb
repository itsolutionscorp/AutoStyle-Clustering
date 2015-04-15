def combine_anagrams(words)
#  puts words
  groups = []
  words.each do |word|
    groups.each do |group|
       a = group[0].upcase.split(//).sort.join
       b = word.upcase.split(//).sort.join
#      puts a + b
       if a == b
#          puts "group << word " + word
          group << word
          word = ""
          break
       end
    end
    if word != ""
#      puts "groups << [word] " + word
      groups << [word]
    end
  end  
  return groups
end


#print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']) 
#puts '[["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]'
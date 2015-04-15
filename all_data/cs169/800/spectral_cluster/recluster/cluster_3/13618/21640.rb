#!/usr/local/bin/ruby


# building the dictionary - containing the list of words to check against

def combine_anagrams (words)
  if(words.length == 0) 
    return words
  end

  anagram_list = [];
  
  print words
  puts

  words = words.flatten

#words = words & words

  words.each do |word|
    if(anagram_list.length == 0)
    then
        anagram_list << [word];
        next
    end

    sorted_word = word.downcase.scan(/./).sort.join

    print sorted_word

#    word_exists = anagram_list.flatten.select { |anag| anag =~ /#{word}/ }
#if (word_exists != nil && word_exists.length == 1)
#next
#end
    
    inserted = false
    anagram_list.each_index do |i|
      print  " " 
      print  anagram_list[i][0].downcase.scan(/./).sort.join
      print "\n"
      if(anagram_list[i][0].downcase.scan(/./).sort.join == sorted_word)
      then
          anagram_list[i] << word
          inserted = true
          break
      end
    end
    anagram_list << [ word ] unless (inserted)
  end
  return anagram_list;
end


array = combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']);
print array
puts

array1 = combine_anagrams(['caRs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']);
print array1
puts

array2 = combine_anagrams([]);

print array2
puts

array1 = combine_anagrams([["a", "a"], [ "b", "b" ], "c", "d" ])
print array1
puts

array1 =  combine_anagrams ((['a', 'a', 'a', 'A'].sort + [['b', 'b'], ['c'], ['D', 'd']].sort))
print array1
puts

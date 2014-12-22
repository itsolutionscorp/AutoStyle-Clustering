def combine_anagrams(words)
  final_array_of_arrays = []
  index = 0
  words.each do |word_to_add|
    #puts "word to add: "
    #puts word_to_add
    match_made = false
    final_array_of_arrays.each do |anagram_cat|
      #puts "anagram_cat: "
      #puts anagram_cat
      #anagram_cat.each do |anagram|
        #puts "anagram: "
        anagram = anagram_cat[0]
        if anagram.downcase.chars.sort == word_to_add.downcase.chars.sort
          #puts "match made"
          match_made = true
          anagram_cat << word_to_add
          #puts "anagram_cat: "
          #puts anagram_cat
          index = index+1
        end
      #end
    end
    unless match_made
      final_array_of_arrays << Array(word_to_add)
    end
  end
  #puts "final:"
  #puts final_array_of_arrays
  return final_array_of_arrays
end

#combine_anagrams(['cars','for','potatoes','racs','four','scar','creams','scream'])

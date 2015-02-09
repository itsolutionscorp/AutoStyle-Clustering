# Create a list of lists of anagrams, repeat words are not removed, case is kept in the output
def combine_anagrams(words)
  final_anagrams = []
  while words.length > 0
    current_item = words.pop
    anagrams = words.find_all{|item| anagram?(current_item,item)}
    # If no anagrams match our current_item we only add it as a list
    if anagrams.empty?
      final_anagrams << [current_item]
    # If we get matches we add those and the current_item as a list and remove all matching items from the original list
    else
      final_anagrams << ([current_item]+anagrams).sort
      anagrams.each do |anagram|
        words.delete(anagram)
      end
    end
  end
  return final_anagrams.sort
end

# Find an anagram given two strings, case does not matter
def anagram?(word_one, word_two)
  a = word_one.downcase.split('').sort.join
  b = word_two.downcase.split('').sort.join
  a == b
end
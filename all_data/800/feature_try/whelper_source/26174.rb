def combine_anagrams(words)
  final_anagrams = []
  while (words.length > 0) do
    current_item = words.pop
    anagrams = words.find_all { |item| anagram?(current_item, item) }
    if anagrams.empty? then
      (final_anagrams << [current_item])
    else
      (final_anagrams << ([current_item] + anagrams).sort)
      anagrams.each { |anagram| words.delete(anagram) }
    end
  end
  return final_anagrams.sort
end

def anagram?(word_one, word_two)
  a = word_one.downcase.split("").sort.join
  b = word_two.downcase.split("").sort.join
  (a == b)
end


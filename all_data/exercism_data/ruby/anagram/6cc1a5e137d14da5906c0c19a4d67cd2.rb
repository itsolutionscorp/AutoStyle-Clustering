class Anagram
  def initialize(word)
    @word = word
  end
  
  def match(words)
    anagrams = []
    
    # rearrange the word to make it easier to compare it against the input array
    match = downcase_and_sort(@word)
    
    words.each do |word|
      # only match if the words are not the same (regardless of case) and they contain the same characters.
      anagrams << word if word.downcase != @word.downcase && downcase_and_sort(word) == match
    end
    
    anagrams
  end
  
  def downcase_and_sort(word)
    word.downcase.chars.sort.join
  end
end

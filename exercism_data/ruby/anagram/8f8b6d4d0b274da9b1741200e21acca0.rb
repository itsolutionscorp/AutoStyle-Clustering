class Anagram

  def initialize(input_word)
    @word = input_word
  end

  #sort any word
  def sort_word(word)
    word.downcase.chars.sort
  end

  #returns true if words are anagrams
  def anagram_word?(test_word)
    sort_word(@word) == sort_word(test_word)
  end

  #returns matches eliminating diff capitalization
  def match(test_words)
    test_words.select { |test_word| anagram_word?(test_word) unless test_word.downcase == @word }
  end
  
end

class Anagram

  def initialize(word)
  	@word = word
  end
  
  def match(wordlist)
  	wordlist.select {|item| are_anagrams(item, @word) }
  end

  private

  def are_anagrams(word1, word2)
  	word1 = normalize(word1)
  	word2 = normalize(word2)
  	sorted(word1) == sorted(word2) unless word1 == word2
  end

  def normalize(str)
  	str.to_s.downcase
  end

  def sorted(str)
  	str.chars.sort
  end

end

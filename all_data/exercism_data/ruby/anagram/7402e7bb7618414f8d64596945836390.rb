class String
  def sorted_letters
    self.chars.sort
  end
end

class Anagram
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.to_s
  end
  
  def match(wordlist)
    wordlist.find_all { |word| anagram_of_word?(word) }
  end

  def anagram_of_word?(other)
    phrase.downcase.sorted_letters == other.downcase.sorted_letters
  end

end

class Anagram
  def initialize(word)
    @word = word.to_s.downcase
  end
  
  def match(wordlist)
    wordlist.each_with_object([]) do |word, matches|
      matches << word if anagram_of_word?(word.downcase)
    end
  end

  def anagram_of_word?(other_word)
    sorted_letters_of(@word) == sorted_letters_of(other_word)
  end

  def sorted_letters_of(word)
    word.chars.sort
  end
end

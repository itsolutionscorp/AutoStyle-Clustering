class Anagram

  def initialize(word)
    @word = word
  end

  def dissected_word
    @letters = @word.split("").sort
  end

  def match(words)
    anagram = []
    words.each do |word|
      if word.split("").sort == dissected_word
        anagram << word
      end
    end
    anagram
  end

end

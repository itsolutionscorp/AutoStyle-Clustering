class Anagram

  def initialize(word)
    @word = word
  end

  def dissected_word
    letters = split(@word)
  end

  def match(words)
    anagram = []
    words.each do |word|
      if split(word) == dissected_word
        anagram << word
      end
    end
    anagram
  end

  def split(word)
    word.split('').sort
  end

end

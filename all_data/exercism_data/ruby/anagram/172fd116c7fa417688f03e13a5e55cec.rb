class Anagram

  def initialize(word)
    @letters = split(word)
  end

  def match(words)
    anagram = []
    words.each do |word|
      if split(word) == @letters
        anagram << word
      end
    end
    anagram
  end

  def split(word)
    word.split('').sort
  end

end

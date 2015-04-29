class Anagram
  attr_reader :letters

  def initialize(word)
    @letters = letters_from(word)
  end

  def match(words)
    match = []

    words.each do |word|
      word_letters = letters_from(word)
      if letters.sort == word_letters.sort
        match << word
      end
    end

    match
  end

  private

  def letters_from(word)
    word.split("")
  end
end

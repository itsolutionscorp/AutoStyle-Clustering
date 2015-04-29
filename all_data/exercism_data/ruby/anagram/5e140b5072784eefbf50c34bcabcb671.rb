class Anagram
  attr_reader :letters

  def initialize(word)
    @letters = letters_from(word)
  end

  def match(words)
    match = []

    words.each do |word|
      if letters.sort == letters_from(word).sort
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

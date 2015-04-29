class Anagram
  attr_reader :letters

  def initialize(word)
    @letters = letters_from(word)
  end

  def match(words)
    words_collection = words.collect{ |word| letters_from(word) }

    match = []

    words_collection.each do |word|
      if letters.sort == word.sort
        match << word.join
      end
    end

    match
  end

  private

  def letters_from(word)
    word.split("")
  end
end

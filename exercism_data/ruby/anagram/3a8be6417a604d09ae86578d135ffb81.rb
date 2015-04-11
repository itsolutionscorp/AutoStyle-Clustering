class AnagramTester
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    Word.from(words).select { |word| word.anagram_of?(@word) }.map(&:to_s)
  end
end

class Word
  def self.from(words)
    words.map { |word| Word.new(word) }
  end

  def initialize(word)
    @word = word.downcase
  end

  def anagram_of?(other_word)
    self.alphabetized_letters == other_word.alphabetized_letters
  end

  def to_s
    @word
  end

  protected

  def alphabetized_letters
    @word.chars.sort
  end
end

# An anagram doesn't really exist in a vacuum, so I'm not sure what an object
# called 'Anagram' would represent.
Anagram = AnagramTester

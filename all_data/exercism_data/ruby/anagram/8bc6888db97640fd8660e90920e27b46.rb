class Anagram
  attr_reader :word

  def initialize(word)
    @word = Word.new(word)
  end

  def match(candidate_words)
    candidate_words.each_with_object([]) do |candidate, matches|
      candidate_word = Word.new(candidate)

      matches << candidate if word.anagram_of?(candidate_word)
    end
  end
end

class Word
  attr_reader :content

  def initialize(content)
    @content = content.downcase
  end

  def ==(other)
    content == other.content
  end

  def sorted_letters
    content.chars.sort
  end

  def anagram_of?(other)
    self != other &&
      sorted_letters == other.sorted_letters
  end
end

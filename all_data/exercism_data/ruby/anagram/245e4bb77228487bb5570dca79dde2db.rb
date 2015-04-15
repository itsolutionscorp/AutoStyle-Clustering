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
    @content = content
  end

  def sorted_downcase_letters
    content.downcase.each_char.sort
  end

  def anagram_of?(other)
    content.downcase != other.content.downcase &&
      sorted_downcase_letters == other.sorted_downcase_letters
  end
end

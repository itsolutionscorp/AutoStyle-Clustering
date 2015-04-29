class Anagram
  attr_reader :source

  def initialize(source)
    @source = AnagramSource.new(source)
  end

  def match(word_array)
    word_array.select do |word|
      source.anagram_of? word
    end
  end
end


class AnagramSource
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def anagram_of?(word)
    !duplicate?(word) && sort_letters_of(word) == sort_letters_of(subject)
  end

  def sort_letters_of(word)
    word.downcase.chars.sort
  end

  def duplicate?(word)
    word.downcase == subject.downcase
  end
end

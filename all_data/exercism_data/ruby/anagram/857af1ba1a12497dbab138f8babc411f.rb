class Anagram
  def initialize(base_word)
    @base_word = base_word
  end

  def match(word_list)
    word_list.select do |word|
      AnagramValidator.new(@base_word, word).valid?
    end
  end
end

class AnagramValidator
  def initialize(base, test_word)
    @base= Word.new(base)
    @test_word = Word.new(test_word)
  end

  def valid?
    @base.is_not_the_same_as(@test_word) && @base.matches?(@test_word)
  end
end

class Word
  def initialize(word)
    @word = word
  end

  def matches?(test_word)
    sorted_letters == test_word.sorted_letters
  end

  def is_not_the_same_as(test_word)
    to_s != test_word.to_s
  end

  def to_s
    lowercase
  end

  def sorted_letters
    letters.sort
  end

  private

  def letters
    lowercase.split('')
  end

  def lowercase
    @word.downcase
  end
end

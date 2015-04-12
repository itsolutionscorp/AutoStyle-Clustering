class Phrase
  def initialize(str)
    @str = str
  end

  def word_count
    counter_defaulting_to_zero = Hash.new(0)
    words.each_with_object(counter_defaulting_to_zero) do |word, counter|
      counter[canonical_version_of_word(word)] += 1
    end
  end

  private

  NON_WORD_CHARACTERS = /\W+/

  def words
    @str.split(NON_WORD_CHARACTERS)
  end

  def canonical_version_of_word(word)
    word.downcase
  end
end

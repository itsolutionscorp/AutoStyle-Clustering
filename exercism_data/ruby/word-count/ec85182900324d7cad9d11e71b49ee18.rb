class Phrase
  REGEX_WORD = /[\w']+/
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    @word_count ||= build_word_count
  end

  private

  def build_word_count
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  def words
    @words ||= @text.downcase.scan(REGEX_WORD)
  end

end

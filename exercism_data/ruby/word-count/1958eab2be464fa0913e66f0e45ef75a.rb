class Phrase
  REGEX_WORD = /[a-zA-Z0-9']+/

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  private

  def words
    @words ||= @phrase.downcase.scan(REGEX_WORD)
  end

end

class Phrase

  WORD_VALIDATOR = Regexp.new /\w+/

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words_in_phrase.each_with_object(Hash.new(0)) { |word, hsh| hsh[word] += 1 }
  end


  private

  def words_in_phrase
    @phrase.downcase.scan(WORD_VALIDATOR)
  end

end

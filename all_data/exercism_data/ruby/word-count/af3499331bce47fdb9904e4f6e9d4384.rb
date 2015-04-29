class Phrase
  NONWORD_SYMBOLS_REGEX = /[^\w\s,]/.freeze
  WORD_DELIMETER_REGEX = /[\s,]+/.freeze

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    counter = Hash.new 0
    words.each.with_object(counter) { |word, counter| count word, counter }
  end

  private
  def words
    formatted_phrase.split WORD_DELIMETER_REGEX
  end

  def formatted_phrase
    @phrase.gsub NONWORD_SYMBOLS_REGEX, ''
  end

  def count word, counter
    counter[word.downcase] += 1 
  end
end

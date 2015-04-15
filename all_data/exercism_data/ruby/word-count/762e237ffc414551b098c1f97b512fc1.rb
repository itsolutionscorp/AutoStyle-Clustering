class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    WordCounter.new(@phrase).word_count
  end

end

WordCounter = Struct.new(:phrase) do

  # @return [Array] of words in the phrase
  def words
    @words ||= phrase.scan(word_expression)
  end

  # @return [Hash] of words and their frequncy
  # Words are normalized by downcasing them
  def word_count
    words.each_with_object(hash_with_default(0)) do |word, counter|
      counter[word.downcase] += 1
    end
  end

  private

  # @note Sets the default value for an unset key to :value
  #   complicated looking enough that I gave it its own method
  #   or fancypants: Hash.new {|hash,k| hash[k] = value}
  def hash_with_default(value)
    Hash.new(value)
  end

  # @see http://www.geocities.jp/kosako3/oniguruma/doc/RE.txt
  # @note I'd like to use a character class, but I couldn't figure it out
  #   I also toyed with String#unpack which was hopeless
  #   I started with String#split but decided I liked String#scan
  #   to positively select words rather than splitting on non-words
  def word_expression
    /[A-Za-z0-9]+/o
  end
end

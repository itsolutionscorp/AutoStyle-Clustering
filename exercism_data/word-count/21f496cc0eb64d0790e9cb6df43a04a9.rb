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
  # @note Words are normalized by downcasing them
  def words
    @words ||= phrase.downcase.scan(word_expression)
  end

  # @return [Hash] of words and their frequncy
  def word_count
    words.each_with_object(new_counter) do |word, counter|
      counter[word] += 1
    end
  end

  private

  # @note Sets the default value for an unset key to :value
  #   complicated looking enough that I gave it its own method
  #   or fancypants: Hash.new {|hash,k| hash[k] = value}
  #   Defaults to zero if no value is passed in
  def new_counter(value=0)
    Hash.new(value)
  end

  # @see http://www.geocities.jp/kosako3/oniguruma/doc/RE.txt
  # @note I also toyed with String#unpack which was hopeless
  #   I started with String#split but decided I liked String#scan
  #   to positively select words rather than splitting on non-words
  def word_expression
    /[[:alnum:]]+/
  end
end

class Phrase
  PUNCTUATION = /[&!@$%^&":]*/
  WORD_SEPARATORS = /[, ]+/

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count(clean(@phrase))
  end

  private

  def clean(phrase)
    phrase
      .downcase
      .gsub(PUNCTUATION, '')
  end

  def count(phrase)
    phrase
      .split(WORD_SEPARATORS)
      .reduce(Hash.new(0)) do |memo, word|
        memo[word] +=  1
        memo
      end
  end
end

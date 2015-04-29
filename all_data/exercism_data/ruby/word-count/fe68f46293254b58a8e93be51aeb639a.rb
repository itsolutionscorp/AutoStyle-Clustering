class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    result = Hash.new(0)
    split_phrase.each do |word|
      result[word] += 1
    end
    result
  end

private
  def ignore_punctuation
    phrase.gsub(/[^0-9a-z ]/i, ' ')
  end

  def split_phrase
    ignore_punctuation.downcase.split(' ')
  end

end

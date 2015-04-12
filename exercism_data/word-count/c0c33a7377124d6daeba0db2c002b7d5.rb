module Enumerable
  def to_histogram
    each_with_object(Hash.new(0)) { |item, hash| hash[item] += 1; }
  end
end

class Phrase
  NON_WORD_DELIMITER = /\W+/

  def initialize(phrase)
    @words = phrase.to_s.downcase.split(NON_WORD_DELIMITER)
  end

  def word_count
    @word_count ||= @words.to_histogram
  end
end

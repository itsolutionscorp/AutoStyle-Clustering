module Enumerable
  def to_histogram
    each_with_object(Hash.new(0)) { |item, hash| hash[item] += 1; }
  end
end

class Phrase
  NON_WORD_DELIMITER = /\W+/

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.to_s
  end

  def word_count
    @word_count ||= words.to_histogram
  end

  def words
    @words ||= phrase.downcase.split(NON_WORD_DELIMITER)
  end
end

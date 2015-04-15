class Phrase
  include Enumerable
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    each_with_object(Hash.new(0)) { |word, counter| counter[word] += 1 }
  end

  def each
    downcased_phrase.scan(/\w+/) do |word|
      yield word
    end
  end

  private

  def downcased_phrase
    phrase.downcase
  end
end

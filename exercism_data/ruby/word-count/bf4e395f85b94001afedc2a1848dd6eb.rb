require 'forwardable'

class Phrase
  extend Forwardable

  def_delegators :@word_counter, :word_count

  def initialize(text, word_counter_class=AlphanumericWordCounter)
    @word_counter = word_counter_class.new(text)
  end
end

class AlphanumericWordCounter
  def initialize(text)
    @text = text.to_s
  end

  def word_count
    alphanumeric_words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word.downcase] += 1
    end
  end

  private
  attr_reader :text

  def alphanumeric_words
    text.scan(/\w+/)
  end
end

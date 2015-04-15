require 'forwardable'
class PhraseWordDivider

  def self.call(phrase)
    phrase.split(/\W+/)
  end

end

class Words 
  extend Forwardable
  include Enumerable
  def_delegators :@words, :each 

  def initialize(phrase)
    @words = PhraseWordDivider.call(phrase)
  end

end

class Phrase
  attr_reader :words

  def initialize(phrase)
    @phrase = phrase.downcase
    @words = Words.new(@phrase)
  end

  def word_count
    words.each_with_object({}) do |word, word_counter|
      word_counter[word] = words.count(word)
    end
  end

end

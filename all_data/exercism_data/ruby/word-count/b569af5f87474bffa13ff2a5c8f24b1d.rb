require 'pp'

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.reduce(hash_with_default) do |count, word|
      count[word] += 1
      count
    end
  end

  private

  def words
    @phrase.downcase.split(/\W+/)
  end

  def hash_with_default
    Hash.new(0)
  end
end

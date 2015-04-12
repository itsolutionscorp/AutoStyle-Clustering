require 'minitest/autorun'

class Phrase
  attr_accessor :words

  def initialize(input)
    @words = input
  end

  def word_count
    counts = Hash.new(0)
    for word in cleaned
      counts[word] += 1
    end
    counts
  end

  def cleaned
    words.gsub(/\W+/, ' ').downcase.split
  end

end

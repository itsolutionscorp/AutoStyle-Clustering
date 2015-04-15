# encoding: utf-8

class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    words.reduce(Hash.new(0)) do |counts, word|
      counts[word.downcase] += 1
      counts
    end
  end

  def words
    text.scan(/\w+/)
  end
end

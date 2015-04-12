# encoding: utf-8

class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word.downcase] += 1
    end
  end

  private

  def words
    @text.scan(/\w+/)
  end
end

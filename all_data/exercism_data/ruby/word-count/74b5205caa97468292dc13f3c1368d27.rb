class Phrase
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counter|
      counter[word.downcase] += 1
    end
  end

private
  def words
    phrase.scan(/[\w']+/)
  end
end

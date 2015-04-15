class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.to_s.downcase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] += 1
    end
  end

  def words
    phrase.scan /[a-zA-Z0-9]+/
  end
end

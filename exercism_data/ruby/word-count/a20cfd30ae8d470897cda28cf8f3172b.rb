class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    formatted_phrase.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] += 1
    end
  end

  def formatted_phrase
    phrase.downcase.scan(/\w+/)
  end

end

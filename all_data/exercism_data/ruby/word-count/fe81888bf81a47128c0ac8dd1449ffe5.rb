class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_list.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] += 1
    end
  end

  def word_list
    phrase.downcase.scan(/\w+/)
  end

end

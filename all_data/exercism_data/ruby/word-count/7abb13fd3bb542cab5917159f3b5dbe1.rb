class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    Counter.count(gathered_words)
  end

  private
  attr_reader :phrase

  def gathered_words
    phrase.downcase.scan(/\w+/)
  end
end

module Counter
  def self.count(list)
    list.each_with_object(Hash.new(0)) do |member, score_keeper|
      score_keeper[member] += 1
    end
  end
end

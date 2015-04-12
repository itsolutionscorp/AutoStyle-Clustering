class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = gather_words
    Counter.count(words)
  end

  private
  attr_reader :phrase

  def gather_words
    phrase.downcase.scan(/\w+/)
  end
end

class Counter
  def self.count(list)
    score_keeper = Hash.new(0)
    list.each_with_object(score_keeper) do |member, keeper|
      keeper[member] += 1
    end
  end
end

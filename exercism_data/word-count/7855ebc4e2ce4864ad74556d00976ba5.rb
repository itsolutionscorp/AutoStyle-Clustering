class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = gather_words
    Counter.count(words)
  end

  private
  def gather_words
    phrase.downcase.gsub(/[^a-zA-Z\s0-9]/,' ').split(" ")
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

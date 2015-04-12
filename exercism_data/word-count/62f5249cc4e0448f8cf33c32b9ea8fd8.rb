class Phrase
  def initialize(sentance)
    @sentance = sentance
  end

  def word_count
    normalized_words.inject(Hash.new(0)) do |acc, word|
      acc.merge({word => acc[word] += 1})
    end
  end

  private
  attr_reader :sentance

  def normalized_words
    sentance.gsub(/[^[:alnum:]]/, ' ').split(' ').map(&:downcase)
  end
end

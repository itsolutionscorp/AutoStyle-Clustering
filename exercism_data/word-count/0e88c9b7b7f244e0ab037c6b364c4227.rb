class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    normalized_phrase.split.inject(Hash.new(0)) do |histogram, word|
      histogram[word] += 1
      histogram
    end
  end

  private

  def normalized_phrase
    @phrase.gsub(/\W/, ' ').squeeze(' ').downcase
  end
end

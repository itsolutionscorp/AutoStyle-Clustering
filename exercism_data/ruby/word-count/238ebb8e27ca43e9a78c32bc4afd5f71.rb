class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.gsub(/[^\w\s,]/, '').split(/[,\s]+/)
    words.inject(Hash.new(0)) do |count, word|
      count[word] = count[word] + 1
      count
    end
  end
end

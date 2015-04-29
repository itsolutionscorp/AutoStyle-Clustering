class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    @phrase.split(/[^\w']+/).each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.downcase.split(/\W+/).each_with_object(Hash.new(0)) do |(word, count), hash|
      hash[word] += 1
    end
  end
end

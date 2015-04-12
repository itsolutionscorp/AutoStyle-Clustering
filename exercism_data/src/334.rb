class Phrase
  def initialize(phrase)
    @words = phrase.downcase.split(/\W+/)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word, score|
      score[word] += 1
    end
  end
end

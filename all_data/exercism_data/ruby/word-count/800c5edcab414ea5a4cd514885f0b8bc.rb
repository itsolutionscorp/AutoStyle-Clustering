class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.downcase.scan(/\b\w+\b/).each_with_object(Hash.new(0)) { |a,b| b[a] += 1 }
  end
end

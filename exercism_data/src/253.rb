class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase.gsub(/[^a-z0-9']+/," ").split
  end

  def word_count
    @phrase.each_with_object(Hash.new(0)) { |word, acc| acc[word] = acc[word]+1 }
  end
end

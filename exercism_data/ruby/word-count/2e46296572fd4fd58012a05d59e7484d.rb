class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase.gsub(/[^a-z0-9']+/," ").split
  end

  def word_count
    @phrase.reduce(Hash.new(0)) { |acc, word| acc[word] = acc[word]+1; acc }
  end
end

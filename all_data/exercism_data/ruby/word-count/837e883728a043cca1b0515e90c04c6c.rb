class Phrase
  def initialize(phrase)
    @phrase = phrase
  end
  
  def word_count
    alphanumeric_sequences.inject(Hash.new(0)) do |words, word|
      words[word.downcase] += 1
      words
    end
  end
  
  private
  def alphanumeric_sequences
    @phrase.split(/[^\w\d]+/)
  end
end

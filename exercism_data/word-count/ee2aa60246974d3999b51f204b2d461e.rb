class Phrase
  def initialize(phrase)
    @phrase = phrase
  end
  
  def word_count
    alphanumeric_sequences.each_with_object(Hash.new(0)) do |word, words|
      words[word.downcase] += 1
    end
  end
  
  private
  def alphanumeric_sequences
    @phrase.split(/[^\w\d]+/)
  end
end

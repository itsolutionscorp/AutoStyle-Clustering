class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    word_array.each_with_object(Hash.new(0)) { |word, word_count| word_count[word] += 1 }
  end

  private

  attr_reader :phrase
  
  def word_array
    phrase.downcase.gsub(/[^a-z\d\"'"s]/, ' ').split(" ")
  end

end

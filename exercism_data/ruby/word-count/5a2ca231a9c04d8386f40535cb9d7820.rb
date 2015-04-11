class Phrase

  def initialize phrase
    @phrase = phrase.downcase.gsub(/[^a-z\d\"'"s]/, ' ')
  end

  def word_count
    word_array = phrase.split(" ")
    word_count = Hash.new(0)
    word_array.each { |word| word_count[word] += 1 }
    word_count
  end

  private

  attr_accessor :phrase

end

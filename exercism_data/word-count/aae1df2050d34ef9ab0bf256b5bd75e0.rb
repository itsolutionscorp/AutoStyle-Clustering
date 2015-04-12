class Phrase

  attr_reader :word_count

  def initialize(phrase)
    @phrase = phrase.downcase.gsub(',', ' ').gsub(/[^a-z0-9'\s]/i, '').split(' ')
    @word_count = @phrase.zip( @phrase.map { |word| @phrase.count(word) } ).to_h
  end

end

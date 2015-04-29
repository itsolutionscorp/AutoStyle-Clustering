class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_hash = Hash.new(0)
    tally_words
    word_hash
  end

  private

  attr_reader :phrase, :word_hash

  def tally_words
    cleaned_phrase.each { |word| word_hash[word.downcase] += 1 }
  end

  def cleaned_phrase
    phrase.gsub(/\W/, ' ').split
  end
end

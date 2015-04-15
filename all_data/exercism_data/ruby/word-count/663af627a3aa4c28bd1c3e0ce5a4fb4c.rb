class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    split_into_words.inject(Hash.new(0)) { |hash, index| hash[index] += 1; hash }
  end

  private

  def split_into_words
    phrase.downcase.gsub(/[^a-z'0-9]/, ' ').split(' ')
  end
end

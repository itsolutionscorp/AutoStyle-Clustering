class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    result_set = Hash.new(0)
    split_into_words.each do |word|
      result_set[word] += 1
    end
    result_set
  end

  private

  def split_into_words
    phrase.downcase.gsub(/[^a-z'0-9]/, ' ').split(' ')
  end
end

class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = Hash.new(0)
    downcased_words_in_phrase.each do |word|
      next if word.empty?
      count[word] += 1
    end
    count
  end

  private

  def downcased_words_in_phrase
    phrase.downcase.split(/\W/)
  end
end

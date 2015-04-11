class Phrase

  def initialize(word_phrase)
    @word_phrase = word_phrase
  end

  def word_count
    word_hash = Hash.new(0)
    single_word do |word|
      word_hash[word] += 1
    end
    word_hash
  end

  private

  attr_reader :word_phrase

  def single_word
    word_phrase.downcase.scan(/[\w']+/) do |word|
      yield word
    end
  end

end

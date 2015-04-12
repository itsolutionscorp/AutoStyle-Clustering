class Phrase

  attr_reader :word_count

  def initialize phrase
    @word_count = Hash.new(0)
    populate_word_count(phrase)
  end

  def add_word word
    @word_count[word] += 1
  end

  def populate_word_count phrase
    phrase_array = phrase.downcase.scan(/\w+/)
    phrase_array.each do |word|
      add_word word
    end
  end
end

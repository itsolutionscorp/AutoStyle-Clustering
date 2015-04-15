class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    @word_count || populate_word_count(@phrase) && @word_count
  end

  private

  def add_word word
    @word_count[word] += 1
  end

  def populate_word_count phrase
    @word_count = Hash.new(0)
    phrase_array = phrase.downcase.scan(/\w+/)
    phrase_array.each do |word|
      add_word word
    end
  end
end

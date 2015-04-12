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
    phrase_array = remove_extraneous_chars phrase
    phrase_array.each do |word|
      add_word word
    end
  end

  def remove_extraneous_chars string
    string.downcase.scan(/\w+/)
  end
end

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
    words = extract_word_list phrase
    words.each do |word|
      add_word word
    end
  end

  def extract_word_list phrase
    phrase.downcase.scan(/\w+/)
  end
end

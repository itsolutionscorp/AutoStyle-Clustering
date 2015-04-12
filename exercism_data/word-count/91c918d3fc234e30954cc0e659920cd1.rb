class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count_words(@phrase)
  end

  private

  def count_words(string)
    @word_list = {}
    string.scan(/[\w]+/).each do |text|
      word = text.downcase
      word_exists_on_list?(word) ? increment_existing_word(word) : add_word_to_list(word)
    end
    @word_list
  end

  def increment_existing_word(word)
    @word_list[word] += 1
  end

  def add_word_to_list(word)
    @word_list[word] = 1
  end

  def word_exists_on_list?(word)
    @word_list.include?(word)
  end

end

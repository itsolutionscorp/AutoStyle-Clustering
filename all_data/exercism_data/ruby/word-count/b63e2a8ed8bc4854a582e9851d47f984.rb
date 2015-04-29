class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    word_counter = WordCounter.new(@text)
    word_counter.word_make_up
  end
end

class WordCounter
  def initialize(text)
    @text = text
  end

  def word_make_up
    count_words(build_word_list)
  end

private

  def build_word_list
    @text.scan(/\w+/).map(&:downcase)
  end

  def count_words(list)
    list.each_with_object(Hash.new(0)){|i,a| a[i] += 1}
  end
end

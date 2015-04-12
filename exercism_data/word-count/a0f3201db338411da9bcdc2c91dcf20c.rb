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
    @text.gsub(/\W/, ' ').split.map { |word| word.downcase}
  end

  def count_words(list)
    counts = Hash.new(0)
    list.each {|word| counts[word] += 1 }
    counts
  end
end

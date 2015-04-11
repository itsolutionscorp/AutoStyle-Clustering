class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    word_counter = WordCounter.new(word_list)
    word_counter.word_make_up
  end

  private

  def word_list
    @text.downcase.scan(/\w+/)
  end
end

class WordCounter
  def initialize(word_list)
    @word_list = word_list
  end

  def word_make_up
    count_occurances @word_list
  end

private

  def count_occurances(words)
    words.each_with_object(Hash.new(0)) do |word,word_count| 
      word_count[word] += 1
    end
  end
end

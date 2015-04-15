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
    @text = text.downcase
  end

  def word_make_up
    count_occurances word_list
  end

private

  def word_list
    @text.scan(/\w+/)
  end

  def count_occurances(list)
    list.each_with_object(Hash.new(0)) do |word,hash| 
      hash[word] += 1
    end
  end
end

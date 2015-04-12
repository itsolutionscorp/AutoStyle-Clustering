class Phrase

  def initialize(phrase)
    @phrase = remove_punctuation(phrase)
    @frequency = {}

    count_words
  end

  def words
    @phrase.split
  end

  def add_entry(word)
    @frequency[word] = @frequency[word] ? @frequency[word] += 1 : 1
  end

  def word_count
    @frequency
  end

private

  def remove_punctuation(phrase)
    phrase.downcase.gsub /[^a-z0-9\' ]+/, ' '
  end

  def count_words
    words.each do |n|
      add_entry(n)
    end
  end
end

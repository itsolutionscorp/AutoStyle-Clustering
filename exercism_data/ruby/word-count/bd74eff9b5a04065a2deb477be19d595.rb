class Phrase
  attr_reader :phrase
  def initialize(new_phrase)
    @phrase = new_phrase
  end

  def word_count
    word_tally = Hash.new(0)
    words.each do |word|
      word_tally[word] += 1
    end
    word_tally
  end

  private

  def words
    words_list = extract_words(phrase)
    normalize(words_list)
  end

  def extract_words(phrase)
    phrase.gsub(/[^[:alnum:] ]/, '').split
  end

  def normalize(words_list)
    words_list.map { |word|
      word.downcase
    }
  end
end

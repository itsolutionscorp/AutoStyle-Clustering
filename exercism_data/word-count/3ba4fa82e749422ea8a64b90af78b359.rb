class Phrase

  NOT_A_WORD_REGEX = /[^a-zA-Z0-9']+/

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def words
    @phrase.split(NOT_A_WORD_REGEX)
  end

  def word_count
    words.each_with_object(Hash.new) do |word, word_counts| 
        word_counts[word] = word_counts[word].to_i + 1
    end
  end

end

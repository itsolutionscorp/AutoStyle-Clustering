class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = find_and_sanitize_words

    collected_words = words.collect do |word|
      [ word, find_occurances(word) ]
    end

    Hash[collected_words]
  end


  private

  def find_occurances(word)
    @phrase.downcase.scan(/\b#{word}\b/).length || 0
  end

  def find_words
    @phrase.downcase.split(/[^\w'?]/)
  end

  def remove_blanks(words)
    words.delete("")
  end

  def find_and_sanitize_words
    all_words = find_words
    remove_blanks(all_words)
    all_words.uniq
  end
end

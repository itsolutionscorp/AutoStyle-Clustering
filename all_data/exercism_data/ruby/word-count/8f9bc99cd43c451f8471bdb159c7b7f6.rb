class Phrase
  def initialize (phrase)
    @phrase = sanitize_text(phrase)
    @words = split_on_whitespace(@phrase)
  end
  
  def sanitize_text (phrase)
    phrase.downcase.gsub(/[^,\w\s]/, "")
  end
  
  def split_on_whitespace (phrase)
    phrase.split(/[\s,]+/)
  end
  
  def word_count
    @words.each_with_object(Hash.new(0)) {
      |word, counted_words| counted_words[word] += 1
    }
  end
end

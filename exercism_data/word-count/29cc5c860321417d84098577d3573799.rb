class Phrase
  def initialize(phrase)
    @phrase = sanitize_text(phrase)
    @words = split_on_whitespace(@phrase)
  end
  
  def sanitize_text(phrase)
    # force string to lowercase and remove all non-alphanumeric, whitespace, and apostrophe characters
    phrase.downcase.gsub(/[^a-zA-Z0-9,\'\s]/, "")
  end
  
  def split_on_whitespace(phrase)
    phrase.split(/[\s,]+/)
  end
  
  def word_count()
    @words.each_with_object(Hash.new(0)) { |word, countedWords| countedWords[word] += 1 }
  end
end

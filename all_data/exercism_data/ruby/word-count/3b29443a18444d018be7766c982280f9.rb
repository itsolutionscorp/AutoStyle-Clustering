class Phrase
  WORD_CHARACTERS = "a-z0-9'"
  WORD_SEPARATORS = " ,"

  def initialize(phrase)
    @phrase = preprocess(phrase)
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end

private
  attr_reader :phrase

  def preprocess(string)
    string.downcase.gsub(/[^#{WORD_CHARACTERS}#{WORD_SEPARATORS}]/, "")
  end

  def words
    phrase.split(/[^#{WORD_CHARACTERS}]+/)
  end
end

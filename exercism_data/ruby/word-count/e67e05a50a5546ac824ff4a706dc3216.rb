class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = clean_phrase(phrase)
  end

  def word_count
    words = phrase.split(" ")
    words.uniq.inject({}) { |result, word| result[word] = words.count(word); result }
  end

  def clean_phrase(phrase)
    phrase.downcase
          .gsub(/[^\w\s\d,']/, "")
          .gsub(/,/, " ")
  end

end

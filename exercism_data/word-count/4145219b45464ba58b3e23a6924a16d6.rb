class Phrase
  def initialize(statement)
    @statement = statement
  end

  def word_count
    word_totals = {}
    parsed_words.each do |word|
      if word_totals.include?(word)
        word_totals[word] += 1
      else
        word_totals.store(word, 1)
      end
    end
    word_totals
  end

  def parsed_words
    phrases = @statement.gsub(/,/, " ")
    words = phrases.gsub(/[^a-zA-Z\s\d\']/, "")
    words.downcase.split(" ")
  end

end

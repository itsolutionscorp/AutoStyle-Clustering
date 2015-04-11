class Phrase
  def initialize(statement)
    @statement = statement
  end

  def word_count
    parsed_words.each_with_object({}) do |word, totals|
      totals.include?(word) ? totals[word] +=1 : totals.store(word, 1)
    end
  end

  def parsed_words
    phrases = @statement.gsub(/,/, " ")
    words = phrases.gsub(/[^a-zA-Z\s\d\']/, "")
    words.downcase.split(" ")
  end

end

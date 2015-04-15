class Phrase
  attr_reader :statement
  def initialize(statement)
    @statement = statement
  end

  def word_count
    words.each_with_object({}) do |word, h|
      h[word] ||= 0 
      h[word] += 1
    end
  end

  private

  def words
    normalized_statement_without_punctuation.split(/\s+/)
  end

  def normalized_statement_without_punctuation
    statement.downcase.gsub(/[^\sa-zA-Z0-9]/, ' ')
  end
end

class Words
  attr_accessor :statement

  def initialize(statement)
    self.statement = statement
  end

  def count
    statement_words.reduce(Hash.new(0)) do |word_counts, word|
      word_counts[word.downcase] += 1
      word_counts
    end
  end

  private

  def statement_words
    statement.scan(/\w+/)
  end
end

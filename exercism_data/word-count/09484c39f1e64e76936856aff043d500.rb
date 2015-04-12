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
    statement.downcase.scan /\w+/
  end
end

class Phrase
  attr_reader :statement
  def initialize(statement)
    @statement = statement
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, h|
      h[word] += 1
    end
  end

  private

  def words
    statement.downcase.scan /\w+/
  end
end

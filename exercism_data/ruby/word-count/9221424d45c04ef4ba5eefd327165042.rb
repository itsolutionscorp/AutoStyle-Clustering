class Phrase
  def initialize input
    @input = input
  end

  def word_count
    @word_count ||= words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  private

  attr_reader :input

  def words
    @words ||= input.downcase.scan /\w+/
  end
end

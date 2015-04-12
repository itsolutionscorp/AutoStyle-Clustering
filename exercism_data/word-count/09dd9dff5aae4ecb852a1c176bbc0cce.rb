class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    @counts ||= words.each_with_object(Hash.new(0)) do |word, memo|
      memo[word] += 1
    end
  end

  private

  def words
    @words ||= @input.downcase.scan /\w+/
  end
end

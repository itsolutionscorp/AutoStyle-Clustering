class Phrase
  attr_reader :input
  def initialize(input)
    @input = input
  end

  def word_count
    word_map = Hash.new(0)

    words.each_with_object(word_map) do |word, map|
      map[word] += 1
    end
  end

  private
  def words
    input.downcase.scan(/\w+/)
  end
end

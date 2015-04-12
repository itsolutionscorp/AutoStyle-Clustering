class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  def words
    @input.downcase.scan(/[a-z0-9]+/)
  end
end

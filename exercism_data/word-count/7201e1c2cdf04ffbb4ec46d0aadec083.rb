class Phrase
  attr_reader :source

  def initialize(source)
    @source = source
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, counts|
      counts[word] += 1
    }
  end

  private

  def words
    normalized_source.scan(/\w+/)
  end

  def normalized_source
    source.downcase
  end
end

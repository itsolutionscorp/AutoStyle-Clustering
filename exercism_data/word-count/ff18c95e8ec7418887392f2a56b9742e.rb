class Phrase
  def initialize(source)
    @source = source
  end

  def word_count
    split_words.each_with_object(Hash.new(0)) { |word, result| result[word] += 1 }
  end

  private

  attr_reader :source

  def split_words
    source.downcase.scan(/\w+/)
  end
end

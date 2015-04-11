class Phrase
  def initialize(source)
    @source = source
  end

  def word_count
    result = Hash.new(0)
    split_words.each do |word|
      result[word] += 1
    end
    result
  end

  private

  def source
    @source
  end

  def split_words
    source.downcase.scan(/\w+/)
  end
end

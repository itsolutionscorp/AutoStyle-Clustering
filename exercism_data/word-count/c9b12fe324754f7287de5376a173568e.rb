class Phrase
  def initialize words
    @words= words
  end

  def word_count
    normalize_case.split_words.each_with_object Hash.new(0) do |word, result|
      result[word] += 1
    end
  end

  private
  def normalize_case
    @words.downcase!
    self
  end

  protected
  def split_words
    @words.split /\W+/
  end
end

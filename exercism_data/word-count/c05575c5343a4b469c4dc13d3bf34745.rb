class Words

  def initialize words
    @words = parse words
  end

  def count
    @words.each_with_object(Hash.new(0)) { |word, result| result[word] += 1 }
  end

  private

  def parse words
    words.downcase.split(/[\W\s]+/)
  end

end

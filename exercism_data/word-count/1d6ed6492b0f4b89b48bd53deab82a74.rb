class Words
  attr_reader :words

  def initialize(words)
    @words = words
  end

  def count
    @count ||= normalized_words.inject(Hash.new(0)) do |memo, word|
      memo[word] += 1
      memo
    end
  end

private

  def normalized_words
    words.downcase.gsub(/\W+/, ' ').split(' ')
  end
end

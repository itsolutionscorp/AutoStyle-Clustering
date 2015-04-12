class Words

  attr_reader :words

  def initialize(words)
    @words = words.downcase
  end

  def count
    clean_words.inject(Hash.new(0)) do |counts, word|
      counts[word] += 1
      counts
    end
  end

  def clean_words
    cleaned = words.split(" ").map { |word| word.gsub(/[^0-9a-z ]/i, '')}
    cleaned.delete("")
    cleaned
  end

end

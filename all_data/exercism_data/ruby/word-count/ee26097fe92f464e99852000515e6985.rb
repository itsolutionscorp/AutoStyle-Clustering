class Words

  attr_accessor :words

  def initialize(words)
    @words = normalize(words)
  end

  def count
    counts = {}
    words.each do |word|
      counts[word] = occurrences_of(word)
    end
    counts
  end

  private

  def occurrences_of(word)
    words.count(word)
  end

  def normalize(words)
    words.gsub(/\W+/, ' ').downcase.split(" ")
  end
end

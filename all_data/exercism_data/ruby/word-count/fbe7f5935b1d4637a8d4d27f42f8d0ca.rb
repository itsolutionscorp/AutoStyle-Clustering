class Words

  attr_accessor :words

  def initialize(words)
    @words = normalize(words)
  end

  def count
    counts = {}
    words.each do |word|
      counts[word] = occurrence_of(word)
    end
    counts
  end

  private

  def occurrence_of(word)
    words.count(word)
  end

  def normalize(words)
    words.gsub(/\W+/, ' ').downcase.split(" ")
  end
end

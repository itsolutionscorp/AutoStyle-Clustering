class Words

  attr_accessor :words

  def initialize(words)
    @words = strip(words)
  end

  def count
    counts = {}
    words.each do |word|
      counts[word] = occurances_of(word)
    end
    counts
  end

  private

  def occurances_of(word)
    words.count(word)
  end

  def strip(words)
    words.gsub(/\W+/, ' ').downcase.split(" ")
  end
end

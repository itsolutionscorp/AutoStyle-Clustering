class Phrase

  attr_reader :words

  def initialize(words)
    @words = words
  end

  def word_count
    clean!
    word_map = words.split
    results = {}
    word_map.each do |word|
      results[word.downcase] = words.scan(/\b#{word}\b/i).length
    end
    results
  end

  private

  def clean!
    words.gsub!(/[^\w\s]/, ' ')
  end
end

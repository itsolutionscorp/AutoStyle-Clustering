class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    split_phrase.inject(Hash.new(0)) do |accumulator, word|
      accumulator[word.downcase.strip] += 1
      accumulator
    end
  end

  def split_phrase
    @phrase.gsub(/:|!|&|@|\$|%|\^|\.|,/, " ").split
  end
end

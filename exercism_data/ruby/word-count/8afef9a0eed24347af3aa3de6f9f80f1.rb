class Phrase
  def initialize(words)
    @words = words
  end

  attr_reader :words

  def word_count
    words.to_enum(:scan, /\w+/).with_object(Hash.new(0)) do |word, counts|
      counts[word.downcase] += 1
    end
  end
end

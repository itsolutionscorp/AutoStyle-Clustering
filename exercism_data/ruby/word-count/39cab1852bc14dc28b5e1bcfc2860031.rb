class Phrase
  attr_reader :words

  def initialize(phrase)
    phrase2 = phrase.gsub(/[^\w\,\'\s]/, "")
    phrase5 = phrase2.split(/[\s\,]/)
    @words = phrase5.select{ |word| word != "" }.map(&:downcase)
  end

  def word_count
    words.reduce(Hash.new(0)) do |hsh, word|
      hsh[word] += 1
      hsh
    end
  end
end

class Phrase

  def initialize(phrase)
    @words = phrase.split(/\W+/)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word.downcase] += 1
    end
  end
end

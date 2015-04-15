class Phrase
  def initialize(phrase)
    @words = phrase.downcase.gsub(/[^\w\s']/, ' ').split(/\s+/)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end
end

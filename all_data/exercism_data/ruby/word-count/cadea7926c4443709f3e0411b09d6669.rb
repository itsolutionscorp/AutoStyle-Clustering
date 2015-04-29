class Phrase
  def initialize(string)
    @words = string.scan(/\w+/)
  end

  def word_count
    @words.map(&:downcase).each_with_object(Hash.new(0)) do |word, word_count|
      word_count[word] += 1
    end
  end
end

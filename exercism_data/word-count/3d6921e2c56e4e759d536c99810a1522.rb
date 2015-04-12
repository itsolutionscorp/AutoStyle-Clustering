class Phrase

  def initialize(phrase)
    @words = phrase.nil? ? {} : phrase.downcase.scan(/\w+/)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

end

class Phrase
  def initialize(string)
    @array_of_words = string.scan(/\w+/)
  end

  def word_count
    @array_of_words.each_with_object(Hash.new(0)) do |word, count|
      count[word.downcase] += 1
    end
  end
end

class Phrase
  def initialize(string)
    @string = string
  end
  
  def word_count
    words = find_words
    words.each_with_object(Hash.new(0)) do |current, count|
      count[current] += 1
    end
  end

  def find_words
    @string.downcase.scan(/\w+/)
  end
end

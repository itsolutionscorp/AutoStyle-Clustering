class Phrase
  def initialize(string)
    @string = string.downcase
  end

  def word_count
    words = @string.split(/\W+/)
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

end

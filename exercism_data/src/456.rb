class Phrase

  def initialize string
    @string = string
  end

  def word_count
    words = Hash.new(0)
    @string.split(/\W*\s\W*|,|\.|\W{2,}/).each { |word| words[word.downcase] += 1 }
    words
  end
end

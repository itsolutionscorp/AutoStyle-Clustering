class Phrase

  def initialize string
    @string = string
    @words = nil
  end

  def word_count
    fill_words unless @words 
    return @words
  end

  def fill_words
    @words = {}
    @string.split(/\W*\s\W*|,|\.|\W{2,}/).each do |word|
      word.downcase!
      if @words.has_key?(word)
        @words[word] += 1
      else
        @words[word] = 1
      end
    end
  end
end

class Anagram
  attr_reader :word, :word_key

  def initialize(word)
    @word = word.downcase
    @word_key = key(@word)
  end

  def match(word_list)
    matches = []
    word_list.each do |candidate|
      lowercase_candidate = candidate.downcase
      next if lowercase_candidate == word
      matches << candidate if key(lowercase_candidate) == word_key
    end
    return matches 
  end

  private

    def key(word)
      word.unpack("c*").sort
    end
end

class Phrase

  def initialize(phrase)
    regex = /[^a-zA-Z0-9, ']/
    @phrase = phrase.gsub(regex, "")
  end

  def word_count
    @dict = {}
    words = @phrase.split(/[ ,]+/)
    words.each do |word|
      w = word.downcase
      @dict[w] = (@dict[w] && (@dict[w] + 1)) || 1
    end
    @dict
  end
end

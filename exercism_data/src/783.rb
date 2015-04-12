class Phrase

  def initialize(phrase)
    nonalphanumeric = /[^a-zA-Z0-9, ']/
    @phrase = phrase.gsub(nonalphanumeric, "")
  end

  def word_count
    @dict = {}
    words = @phrase.split(/[ ,]+/)
    words.each do |word|
      w = word.downcase
      @dict[w] ||= 0
      @dict[w] = @dict[w] + 1
    end
    @dict
  end
end

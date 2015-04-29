class Phrase
  attr_reader :word_count

  def initialize(str)
    @word_count = {}
    fill_count(str)
  end

  private

  def fill_count(str)
    words = str.split(/[\W]+/)
    words.each do |word|
      word.downcase!
      @word_count.has_key?(word) ? @word_count[word] += 1 : @word_count[word] = 1
    end
  end
end

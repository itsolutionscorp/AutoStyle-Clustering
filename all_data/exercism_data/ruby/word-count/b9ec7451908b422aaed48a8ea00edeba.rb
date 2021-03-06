class Phrase
  attr_reader :word_count

  def initialize(str)
    @word_count = Hash.new(0)
    fill_count(str)
  end

  private

  def fill_count(str)
    words = str.downcase.split(/\W+/)
    words.each do |word|
      @word_count[word] += 1
    end
  end
end

class Phrase
  attr_reader :words

  def initialize(input)
    @words = (String input).downcase.scan(/[[:alnum:]]+/)
    @word_count_map = Hash.new(0)
    build_word_count_map
  end

  def word_count
    @word_count_map
  end

  private
  def build_word_count_map
    words.each do |w|
      @word_count_map[w] += 1
    end
  end
end

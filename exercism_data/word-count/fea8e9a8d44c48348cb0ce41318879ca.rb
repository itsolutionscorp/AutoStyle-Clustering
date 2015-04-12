class Phrase
  attr_reader :text

  def initialize(input)
    @text = (String input).downcase.scan(/[[:alnum:]]+/)
    @word_count_map = Hash.new(0)
    build_word_count_map
  end

  def word_count
    @word_count_map
  end

  private
  def build_word_count_map
    text.each do |w|
      @word_count_map[w] += 1
    end
  end
end

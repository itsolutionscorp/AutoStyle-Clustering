class Phrase
  attr_reader :text

  def initialize(input)
    @text = drop_non_alphanumeric(String input)
    @word_count_map = {}
    build_word_count_map
  end

  def word_count
    @word_count_map
  end

  private
  def build_word_count_map
    text.split(/[[:space:]]+/).each do |w|
      w.downcase!
      @word_count_map[w] = (@word_count_map[w] || 0) + 1
    end
  end

  def drop_non_alphanumeric(input)
    input.gsub(/[^[:alnum:]]/,' ')
  end

end

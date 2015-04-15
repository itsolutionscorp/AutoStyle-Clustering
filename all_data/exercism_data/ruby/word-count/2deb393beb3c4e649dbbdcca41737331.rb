class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words_with_count = parse_words.group_by{|x| x}.map{|word, word_group| [word, word_group.length]}
    Hash[words_with_count]
  end

  private

  def parse_words
    @text.scan(/\w+/).map(&:downcase)
  end
end

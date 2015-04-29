class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    word_groups = parse_words.group_by{|x| x}
    words_with_count = word_groups.map{|word, word_group| [word, word_group.length]}
    Hash[words_with_count]
  end

  private

  def parse_words
    @text.scan(/\w+/).map(&:downcase)
  end
end

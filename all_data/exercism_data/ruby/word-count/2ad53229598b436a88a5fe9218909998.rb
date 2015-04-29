class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words = @text.scan(/\w+/).map(&:downcase)
    words_with_count = words.group_by{|x| x}.map{|word, word_group| [word, word_group.length]}
    Hash[words_with_count]
  end
end

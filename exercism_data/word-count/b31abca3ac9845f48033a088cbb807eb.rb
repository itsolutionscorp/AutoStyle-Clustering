class Phrase
  attr_reader :word_count, :line

  def initialize(line)
    @line = line
    @word_count = count_words
  end

  def count_words
    grouped_words.each_with_object({}) do |words, list|
      word, occurances = *words
      list[word] = occurances.size if word
    end
  end

  def grouped_words
    line.downcase.split.group_by{|fragment| fragment[acceptable_characters]}
  end

  def acceptable_characters
    /\w+/
  end
end

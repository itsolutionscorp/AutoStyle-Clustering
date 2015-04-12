class Phrase

  def initialize(text)
    @words = text
  end

  def word_count
    @counts = parse_input.group_by{|word|word}
    count_words
    @counts
  end

  private

  def count_words
    @counts.each{ |key, words| @counts[key] = words.count }
  end

  def parse_input
    @words.downcase.split(/\W+/)
  end
end

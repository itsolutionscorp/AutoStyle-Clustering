class Phrase

  def initialize(text)
    @words = text
  end

  def word_count
    @counts = {}
    group_words
    count_words
  end

  private

  def count_words
    @counts.each{ |key, words| @counts[key] = words.count }
    @counts
  end

  def group_words
    @counts = parse_input.group_by{|word|word}
  end

  def parse_input
    @words.downcase.split(/\W+/)
  end
end

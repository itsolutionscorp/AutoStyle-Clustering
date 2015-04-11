class Phrase

  def initialize(text)
    @words = text
  end

  def word_count
    counts = {}
    parse_input
    .group_by{|word|word}
    .each{ |key, words| counts[key] = words.count }
    counts
  end

  private

  def parse_input
    @words.downcase.split(/\W+/)
  end
end

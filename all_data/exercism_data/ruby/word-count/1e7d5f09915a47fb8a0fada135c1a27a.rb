class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.strip.downcase.gsub(/[^a-zA-Z0-9']/, " ").split(" ")
    words.group_by { |i| i }.map { |k, v| { k => v.size } }.reduce(:merge)
  end
end

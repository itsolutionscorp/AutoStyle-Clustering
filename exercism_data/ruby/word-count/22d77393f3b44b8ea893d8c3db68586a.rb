class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = split_phrase
    words.map!{|word| word.downcase.gsub(/[^a-zA-Z0-9]/, '')}
    words.delete_if(&:empty?)
    words.inject({}) do |memo, word|
      memo[word] ||= 0
      memo[word] += 1
      memo
    end
  end

  private
  def split_phrase
    @phrase.split(/\s|,|\.|-/) # split on white-space, commas, full-stops, or hyphens
  end
end

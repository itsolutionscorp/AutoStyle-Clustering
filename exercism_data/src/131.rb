class Phrase
  def initialize(string)
    @input = string
    @groomed_words ||= @input.downcase.gsub( /[^\w\s]/, ' ' ).strip.split
  end

  def word_count
    @groomed_words.uniq.each_with_object({}) { |word, word_totals| word_totals[word] = @groomed_words.count(word) }
  end
end

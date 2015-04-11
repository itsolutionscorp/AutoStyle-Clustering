class Phrase < Struct.new(:words)
  def word_count
    counters = Hash.new { 0 }
    clean_words.each {|word| counters[word] += 1}
    counters
  end

  private

  def clean_words
    words.downcase.split(/\W+/)
  end
end

class Phrase
  attr_reader :word_count

  def initialize input_phrase
    @words      = collect_words(input_phrase)
    @word_count = {}
  end

  def collect_words phrase
    phrase.downcase.gsub(/[^a-z0-9'\s]/, ' ').split
  end

  def word_count
    @words.uniq.each{ |word| @word_count[word] = @words.count(word) }

    @word_count
  end
end

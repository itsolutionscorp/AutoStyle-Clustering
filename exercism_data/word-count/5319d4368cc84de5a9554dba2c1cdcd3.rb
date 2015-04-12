class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    words         = @input.to_s.downcase.scan(/\w+/)
    counted_words = words.uniq.map { |w| [w, words.count(w)] }

    Hash[counted_words]
  end
end

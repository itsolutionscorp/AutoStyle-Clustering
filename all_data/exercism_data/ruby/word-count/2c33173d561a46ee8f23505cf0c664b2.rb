class Phrase

  attr_reader :word_count

  def initialize phrase
    words = phrase.downcase.split(/[\W_]+/)
    @word_count = words.each_with_object(Hash.new()) do |word, values|
      values[word] ||= 0
      values[word] += 1
    end
  end

end

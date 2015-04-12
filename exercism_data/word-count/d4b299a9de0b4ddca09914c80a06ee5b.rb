class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @word_count = phrase.downcase
                        .scan(/\w+/)
                        .each_with_object(Hash.new()) do |word, values|
                    values[word] ||= 0
                    values[word] += 1
                  end
  end
end

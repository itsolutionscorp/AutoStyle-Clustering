class Phrase
  attr_reader :word_count
  def initialize(source_phrase)
    @word_count = source_phrase
                                  .split(/[^A-Za-z0-9]+/)
                                  .each_with_object(Hash.new(0)) { |word, obj| obj[word.downcase] += 1 }
  end
end

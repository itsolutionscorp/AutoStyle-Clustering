class Phrase
  attr_reader :input
  attr_reader :word_counts

  def initialize(input)
    @input = input
  end

  def word_count
    @word_count ||= process_input
  end

  private

  def process_input
    word_parts.inject(Hash.new(0)) do |counts, word|
      counts[word] += 1
      counts
    end
  end

  def word_parts
    input.scan(/\w+/).map(&:downcase)
  end
end

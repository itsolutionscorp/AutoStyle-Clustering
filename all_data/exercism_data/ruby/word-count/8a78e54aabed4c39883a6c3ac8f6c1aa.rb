class Phrase
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    @word_count ||= process_input
  end

  private

  def process_input
    word_parts.each_with_object(Hash.new(0)) do |counts, word|
      counts[word] += 1
    end
  end

  def word_parts
    input.downcase.scan(/\w+/)
  end
end

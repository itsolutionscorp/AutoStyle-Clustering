class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    words.each_with_object(empty_counts) do |word, counts|
      counts[word] += 1
    end
  end

  private

  attr_reader :input

  def words
    input.split(/[,\s]+/)
      .map { |word| word.gsub(/[^a-z0-9]/i, "").downcase }
      .reject(&:empty?)
  end

  def empty_counts
    Hash.new { |hash, word| hash[word] = 0 }
  end
end

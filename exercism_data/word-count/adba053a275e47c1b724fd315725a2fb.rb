require "unicode_utils"

class Phrase
  def initialize input
    @input = input
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  private

  def words
    @input.scan(/\w+/).map(&:downcase)
  end
end
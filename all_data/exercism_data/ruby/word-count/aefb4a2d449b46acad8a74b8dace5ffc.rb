# encoding: utf-8

class Words
  def initialize(string)
    @string = string
  end

  def count
    result = Hash.new { |hash, word| hash[word] = 0 }
    words_in_input.each do |word|
      result[word] += 1
    end
    result
  end

  private

  def words_in_input
    @words_in_input ||= @string.split(/\W+/).map(&:downcase)
  end
end

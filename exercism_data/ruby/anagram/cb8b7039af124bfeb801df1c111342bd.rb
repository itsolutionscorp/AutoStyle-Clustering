class Anagram

  def initialize(source)
    @source = source.downcase
    @source_chars = @source.chars.sort.join
  end

  def match(answers)
    answers.map(&:downcase).uniq.select { |match| contains?(match) && match != @source }
  end

  private

  def contains?(word)
    @source_chars.start_with?(word.chars.sort.join)
  end

end

class Phrase < Struct.new(:phrase)
  def word_count
    words.inject Hash.new(0) do |result, word|
      result[word] += 1
      result
    end
  end

  private

  def words
    @words ||= phrase.scan(/\w+/).map &:downcase
  end
end

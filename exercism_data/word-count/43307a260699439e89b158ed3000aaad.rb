class Phrase < Struct.new(:phrase)
  def word_count
    words.uniq.inject({}) do |result, word|
      result[word] = words.count word
      result
    end
  end

  private

  def words
    @words ||= phrase.scan(/\w+/).map &:downcase
  end
end

class Phrase < String
  def word_count
    words.each_with_object({}) do |word, counts|
      counts[word] ||= 0
      counts[word] += 1
    end
  end

  def words
    normalized.scan(/\w+/)
  end

  alias :normalized :downcase
end

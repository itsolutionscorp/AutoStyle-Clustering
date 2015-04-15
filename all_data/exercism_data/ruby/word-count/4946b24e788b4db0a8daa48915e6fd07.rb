class Phrase < String
  def word_count
    {}.tap do |counts|
      words.each do |word|
        counts[word] ||= 0
        counts[word] += 1
      end
    end
  end

  def words
    normalized.scan(/\w+/)
  end

  alias :normalized :downcase
end

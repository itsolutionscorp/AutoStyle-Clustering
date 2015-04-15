class Phrase < String

  PUNCTUATION = /[^a-z0-9\s]/

  def word_count
    words_only.each_with_object(Hash.new(0)) { |word, counts| 
      counts[word] += 1
    }
  end

  private

  def words_only
    downcase.gsub(PUNCTUATION,'').split
  end

end

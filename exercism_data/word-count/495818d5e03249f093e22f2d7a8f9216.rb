class Phrase < String

  def word_count
    word_count = words.inject(Hash.new(0)) do |result, key| 
      result[key] += 1
      result
    end
  end

  def words
    sanitize_and_regularize.split
  end

  def sanitize_and_regularize
    gsub(%r{\W},' ').downcase
  end

end

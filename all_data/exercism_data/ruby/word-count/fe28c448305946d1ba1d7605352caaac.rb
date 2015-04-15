class Phrase < String

  def word_count
    words.each_with_object(Hash.new(0)) { |key, memo| memo[key] += 1 }
  end

  def words
    sanitize_and_regularize.split
  end

  def sanitize_and_regularize
    gsub(%r{\W},' ').downcase
  end

end

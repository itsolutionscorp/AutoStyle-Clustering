class Phrase < Struct.new(:phrase)
  def word_count
    Hash[counted_words]
  end

  def counted_words
    grouped_words.map do |word, group|
      [word, group.size]
    end
  end

  def grouped_words
    words.group_by { |w| w }
  end

  def words
    phrase_without_punctuation.split(' ')
  end

  def phrase_without_punctuation
    phrase.downcase.gsub(/[^\w']/, ' ')
  end
end

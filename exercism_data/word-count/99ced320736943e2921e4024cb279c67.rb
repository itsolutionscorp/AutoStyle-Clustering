class Phrase < String
  def word_count
    word_list.reduce(Hash.new(0)) { |counts, word| counts[word] = counts[word] + 1; counts }
  end

  private
  def sanitize
    gsub(/[^0-9a-z ]/i, '').downcase
  end

  def word_list
    sanitize.split
  end
end

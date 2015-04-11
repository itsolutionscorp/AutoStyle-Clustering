class Phrase < String
  def word_count
    words.each_with_object(Hash.new(0)) { |word, count| count[word] += 1 }
  end

  def words
    downcase.split(/[^a-zA-Z0-9]+/)
  end
end

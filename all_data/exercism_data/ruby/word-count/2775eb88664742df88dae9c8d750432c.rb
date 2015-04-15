Phrase = Struct.new(:phrase) do
  def word_count
    words.each_with_object(Hash.new(0)) do |word, collector|
      collector[word.downcase] += 1
    end
  end

  def words
    phrase.split(/\W+/)
  end
end

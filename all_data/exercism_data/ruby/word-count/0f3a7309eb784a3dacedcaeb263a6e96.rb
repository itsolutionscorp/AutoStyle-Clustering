Phrase = Struct.new(:phrase) do
  def word_count
    Hash.new(0).tap do |hash|
      words.each { |word| hash[word] += 1 }
    end
  end

  def words
    phrase.split(/\W+/).map &:downcase
  end
end

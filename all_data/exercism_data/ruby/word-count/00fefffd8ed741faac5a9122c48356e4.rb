Phrase = Struct.new(:phrase) do
  def appearances
    @appearances ||= Hash.new(0)
  end

  def word_count
    @word_count ||= appearances.tap do |hash|
      words.each { |word| hash[word] += 1 }
    end
  end

  def words
    phrase.split(/\W+/).map &:downcase
  end
end

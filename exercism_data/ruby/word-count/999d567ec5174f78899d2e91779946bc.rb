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
    phrase.split(/\W+/).collect { |word| word.downcase }
  end
end

p Phrase.new("one fish two fish red fish blue fish").word_count

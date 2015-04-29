class Phrase
  def initialize word
    @word = word
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end

  def words
    @word.downcase.split(/[\W]/).select {|word| !word.empty?}
  end

end

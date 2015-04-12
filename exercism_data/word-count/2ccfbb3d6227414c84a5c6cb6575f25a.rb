class Phrase

  def initialize(phrase)
    @lowercase = phrase.downcase
  end

  def word_count
    word_list.each_with_object(Hash.new(0)) { |word, counts| counts[word] += 1 }
  end

  def word_list
    @lowercase.scan(/\w+/)
  end

end

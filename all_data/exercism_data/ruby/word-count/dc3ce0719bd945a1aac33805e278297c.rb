class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase.scan(/[[:alnum:]]+/)
  end

  def word_count
    @phrase.each_with_object(Hash.new(0)) do |word, dictionary|
      dictionary[word] += 1
    end
  end

end

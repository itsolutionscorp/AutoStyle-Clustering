class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    arr = @phrase.downcase.split(/[\W]+/)

    arr.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end
end

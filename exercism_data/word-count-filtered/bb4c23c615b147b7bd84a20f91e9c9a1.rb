class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.split(/[^\w]+/)
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end
end

class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] = counts[word] + 1
    end
  end

  def words
    @text.downcase.split(/\W+/)
  end
end

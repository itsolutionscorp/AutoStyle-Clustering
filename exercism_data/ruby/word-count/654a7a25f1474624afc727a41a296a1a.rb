class Phrase
  def initialize(content)
    @content = content
  end

  def word_count
    words(@content).each_with_object(Hash.new(0)) { |word, frequencies|
      frequencies[word.downcase] += 1
    }
  end

  private

  def words(content)
    content.scan(/\w+/)
  end
end

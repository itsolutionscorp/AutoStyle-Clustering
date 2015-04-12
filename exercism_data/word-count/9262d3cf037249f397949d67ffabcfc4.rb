class Phrase
  def initialize content
    @content = content
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  def words
    @content.downcase
            .split(/\W+/)
  end
end

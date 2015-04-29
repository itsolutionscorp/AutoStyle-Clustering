class Phrase
  def initialize content
    @content = content
  end

  def word_count
    count = Hash.new(0)
    words.each do |word|
      count[word] += 1
    end
    count
  end

  def words
    @content.downcase
            .split(/\W+/)
  end
end

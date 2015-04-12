class Phrase
  def initialize(content)
    @content = content
  end

  def word_count
    counts = Hash.new(0)
    words.each do |word|
      counts[word] += 1
    end
    counts
  end

  def words
    @content.downcase.scan(/\w+/)
  end
end

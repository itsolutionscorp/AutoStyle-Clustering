class Phrase
  attr_reader :word_count

  def initialize(content)
    @content = content
    @word_count = count
  end

  private

  def count
    counts = Hash.new(0)
    words = @content.downcase.gsub(/[^\w]+/, " ").split(" ")
    words.each { |word| counts[word] += 1 }
    counts
  end
end

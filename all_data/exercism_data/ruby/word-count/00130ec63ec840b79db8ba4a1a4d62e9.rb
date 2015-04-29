class Phrase
  def initialize(string)
    @original_string = string

    @word_histogram = count_words
  end

  def word_count
    @word_histogram
  end




  private
  def count_words
    histogram = Hash.new(0)

    words.each do |word|
      histogram[word.downcase] += 1
    end

    histogram
  end

  private
  def words
    words = @original_string.split(/[\s,]/)

    remove_punctuation(words)
  end

  private
  def remove_punctuation(words)
    words.map { |word| word.gsub(/\W/, "") }.reject { |word| word.empty? }
  end
end

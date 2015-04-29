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
    words = @original_string.scan(/\w+/)
  end
end

class Phrase
  def initialize(string)
    @original_string = string
  end

  def word_count
    words.inject(Hash.new(0)) do |histogram, word|
      histogram[word.downcase] += 1
      histogram
    end
  end




  private
  def words
    @original_string.scan(/\w+/)
  end
end

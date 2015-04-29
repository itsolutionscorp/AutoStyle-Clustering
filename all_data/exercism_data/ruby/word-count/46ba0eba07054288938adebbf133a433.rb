class Phrase
  def initialize(string)
    @original_string = string
  end

  def word_count
    words(@original_string).inject(Hash.new(0)) do |histogram, word|
      histogram[word.downcase] += 1
      histogram
    end
  end




  private
  def words(string = "")
    string.scan(/\w+/)
  end
end

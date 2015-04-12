class Phrase
  def initialize(string)
    @original_string = string
  end

  def word_count
    words(@original_string).each_with_object(Hash.new(0)) do |word, histogram|
      histogram[word.downcase] += 1
    end
  end




  private
  def words(string = "")
    string.scan(/\w+/)
  end
end
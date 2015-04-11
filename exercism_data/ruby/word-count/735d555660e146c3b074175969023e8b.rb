class Phrase
  def initialize(string)
    @original_string = string
  end

  def word_count
    words(@original_string).each_with_object(Hash.new(0)) do |word, histogram|
      histogram[normalize(word)] += 1
    end
  end


  private
  def normalize(word)
    word.downcase
  end

  private
  def words(string = "")
    string.scan(/\w+/)
  end
end

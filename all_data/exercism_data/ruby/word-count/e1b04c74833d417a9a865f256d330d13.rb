class Phrase

  def initialize statement
    @statement = statement
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, res| 
      res[word] += 1
    end 
  end

  private

  def words
    @statement.downcase.scan(/[\w']+/)
  end

end

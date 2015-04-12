class Phrase
  def initialize(input)
    @input = input.to_s
  end
  
  def word_count
    words.reduce(Hash.new(0)) do |acc,word|
      acc[word] += 1
      acc
    end
  end

  private
  def words
    @input.downcase.scan(%r{\w+})
  end
  
end

class Phrase
  def initialize(input)
    @input = input.to_s
  end
  
  def word_count
    word_summary = Hash.new(0)
    words.each_with_object(word_summary) { |word, summary| summary[word] += 1 }
  end

  private
  def words
    @input.downcase.scan(%r{\w+})
  end
  
end

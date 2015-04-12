class Phrase
  def initialize(str)
    @str = str
  end

  def word_count
    words = @str.downcase.gsub(/\w+/)
    result = Hash.new(0)
    words.each { |word| result[word] += 1 }
    result
  end
end
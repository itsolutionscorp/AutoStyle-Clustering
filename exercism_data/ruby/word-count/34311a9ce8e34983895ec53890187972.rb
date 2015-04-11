class Phrase
  def initialize(str)
    @str = str
  end

  def word_count
    words = @str.downcase.gsub(/\w+/)
    result = {}
    words.each do |word|
      if result[word]
        result[word] += 1
      else
        result[word] = 1
      end
    end
    result
  end
end

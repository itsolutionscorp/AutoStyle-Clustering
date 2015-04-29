class Phrase
  def initialize content
    @content = content
  end

  def word_count
    result = {}
    @content.downcase.scan(/\w+'\w+|\w+/).each do |word|
      result[word] ? result[word] += 1 : result[word] = 1 
    end
    result
  end
end

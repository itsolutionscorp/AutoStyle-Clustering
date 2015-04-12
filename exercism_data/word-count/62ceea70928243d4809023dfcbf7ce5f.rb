class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words.reduce({}) do |result, word|
      count_towards result, word
    end
  end

  def count_towards result, word
    if word_count = result[word]
      result[word] = word_count + 1
    else
      result.merge!(word => 1)
    end
    result
  end

  def words
    @text.downcase.gsub(/\W/,' ').split
  end
end

class Phrase
  def initialize text
    @text = text
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  def words
    sanitised_text.downcase.split
  end

  def sanitised_text
    @text.gsub(/,/, ' ').gsub(/[^\w\s']/, '')
  end
end

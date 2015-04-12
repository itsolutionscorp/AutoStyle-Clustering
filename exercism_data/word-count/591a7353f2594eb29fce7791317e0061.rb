class Phrase

  def initialize(text)
    @words = text.downcase.gsub(/\W/,' ').split
  end

  def word_count
    @words.map(&:strip).reduce({}) do |count, word|
      count[word] = count[word].to_i + 1
      count
    end
  end

end

class Words

  def initialize(words)
    @words = words.downcase.gsub(/\W/, " ")
  end

  def count
    counted_words = Hash.new(0)
    parsed_words.each { |word| counted_words[word] += 1 }
    counted_words
  end

  private

  def parsed_words
    @words.split(" ")
  end

end

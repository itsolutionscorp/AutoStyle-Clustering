class Words

  def initialize(words)
    @words = words
  end

  def count
    counted_words = Hash.new(0)
    parsed_words.each { |word| counted_words[word] += 1 }
    counted_words.delete_if {|k,v| k.empty? }
  end

  private

  def cleaned_words
    @words.downcase.gsub(/\W/, " ")
  end

  def parsed_words
    cleaned_words.split(" ")
  end

end

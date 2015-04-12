class Words

  def initialize(words)
    @words = words
  end

  def count
    counted_words = Hash.new(0)
    cleaned_words.map { |word| counted_words[word] += 1 }
    counted_words.delete_if {|k,v| k.empty? }
  end

  private

  def parsed_words
    @words.split(" ")
  end

  def cleaned_words
    cleaned = []
    parsed_words.map { |word| cleaned << word.downcase.gsub(/\W/, "") }
    cleaned
  end

end

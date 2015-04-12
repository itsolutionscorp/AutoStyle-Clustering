class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.strip
           .downcase
           .gsub(/[^a-zA-Z0-9']/, " ")
           .split(" ")
           .each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end
end

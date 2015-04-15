class Phrase
  @@phrase
  def initialize(phrase)
  	@@phrase = phrase
  end

  def word_count()
  	counts = {}
    words = @@phrase.split(/[^a-zA-Z0-9']/).delete_if{|word| word == ""}
    words.each do |word|
      counts.has_key?(word.downcase)? counts[word.downcase] += 1 : counts[word.downcase] = 1
    end
    counts
  end
end

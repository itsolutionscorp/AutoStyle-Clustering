class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.downcase.gsub(",", " ").gsub(/[^a-z|\s|\d|\']/, "")
  end

  def word_count
    counts = {}
    phrase.split(" ").each do |word|
      if counts[word]
        counts[word] += 1
      else
        counts[word] =1
      end
    end
    counts
  end
end

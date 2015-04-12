class Phrase
  attr_reader :phrase, :counts
  def initialize(phrase)
    @phrase = phrase
    @counts = {}
  end

  def word_count
    counts = {}
    phrase.split(/[[:space:]]/).each do |word|
      increment_count(word)
    end
    counts
  end

  def increment_count(word)
    word = normalize_case(remove_punctuation(word))
    count = counts.fetch(word, 0)
    counts[word] = count + 1 unless empty_word?(word)
  end

  def remove_punctuation(word)
    word.gsub(/\W+/, "")
  end

  def normalize_case(word)
    word.downcase
  end

  def empty_word?(word)
    word.empty?
  end
end

class Phrase
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
    clear_existing_counts
  end

  def word_count
    clear_existing_counts
    phrase.scan(/\w+/) do |word|
      increment_count(word)
    end
    @count
  end

  def clear_existing_counts
    @count = Hash.new { |hash, key| hash[key] = "0" }
  end

  def increment_count(word)
    word = normalize_case(word)
    @count[word] = @count.fetch(word, 0) + 1 unless word.empty?
  end

  def normalize_case(word)
    word.downcase
  end
end

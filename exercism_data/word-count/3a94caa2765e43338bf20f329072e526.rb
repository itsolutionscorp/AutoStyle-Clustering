class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    count_words(clean_words(@words))
  end

  def count_words(word_list)
    word_list.inject(make_counting_hash) do |results, word|
      results[word] += 1
      results
    end
  end

  def clean_words(word_string)
    word_string.gsub(/\W/, ' ').split.map(&:downcase)
  end

  def make_counting_hash
    Hash.new {|h, k| h[k] = 0 }
  end
end

class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    dictionary_counter = Hash.new

    normalize_phrase

    @phrase.split(' ').each do |word|
      dictionary_counter[word] = 0 unless dictionary_counter.has_key?(word)
      dictionary_counter[word] += 1
    end

    dictionary_counter
  end

  private 

  def normalize_phrase
    @phrase = @phrase.downcase.gsub(/[^a-z|0-9\s]/, '')
  end

end

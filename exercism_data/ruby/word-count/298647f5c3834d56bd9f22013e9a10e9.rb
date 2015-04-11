class Phrase < String

  def word_count
    sanitized_word_list.inject(Hash.new(0)) { |counter, word| counter[word] += 1 and counter }
  end

  private

  def sanitized_word_list
    downcase.scan(/[a-z0-9]+/)
  end

end

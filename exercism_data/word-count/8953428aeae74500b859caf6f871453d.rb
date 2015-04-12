module IndividualWords

  def scrubbed(words)
    without_apostrophies = not_apostrophied(words)
    apostrophied(words) + not_apostrophied(words)
  end

  def apostrophied(words)
    words.downcase.split.select { |word| word.include?("'") }
  end

  def not_apostrophied(words)
    words_array = words.downcase.split.select { |word| !word.include?("'") }
  end

  def normalized(words)
    cleaned = words.join(' ').gsub(/\W/, ' ')
    cleaned.downcase.split
  end
end

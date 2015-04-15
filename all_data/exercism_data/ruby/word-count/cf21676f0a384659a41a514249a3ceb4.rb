class Phrase < String
  SEPERATOR_REGEX = /[[:space:],]/

  def word_count
    counts = Hash.new 0
    tokenize.each { |word| counts[word] += 1 }
    counts
  end

  def tokenize
    downcase
      .split(SEPERATOR_REGEX)
      .map { |word| word.gsub(/\W/, "") }
      .reject { |word| word == "" }
  end
end

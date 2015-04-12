class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)

    cleaned_phrase = @phrase.downcase

    # Strip out anything that's not a letter, number, apostrophe, comma, or space
    cleaned_phrase.gsub!(/[^a-z0-9',\s]/, '')

    cleaned_phrase.split(/\s|,/).each do |word|
      counts[word] = counts[word] + 1 if word.length > 0
    end

    counts
  end
end

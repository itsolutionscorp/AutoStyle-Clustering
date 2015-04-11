class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    text = strip_unused_chars(@text.downcase)
    text = normalize_to_tight_comma_delimiting(text)

    words = split_on_spaces_or_commas(text)  
    words.inject(Hash.new(0)) do |counts, word|
      counts[word] += 1
      counts
    end
  end

  private

  def strip_unused_chars(text)
    text.gsub(/[^0-9a-z, ]/, '')
  end

  def normalize_to_tight_comma_delimiting(text)
    text.squeeze(' ').gsub(/,[ ]*/, ',')
  end

  def split_on_spaces_or_commas(text)
    text.split(/[ ,]/)
  end

end

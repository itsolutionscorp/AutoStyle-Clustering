class Phrase

  def initialize(content)
    @content = content
  end

  def word_count
    normalized_content = strip_and_normalize(@content)
    count_words(normalized_content)
  end

  private
  def count_words(content)
    count = Hash.new(0)
    split_words(content).each { |word| count[word] += 1 }
    count
  end

  def split_words(content)
    content.split(" ")
  end

  def strip_and_normalize(content)
    phrase = strip_punctuation(content)
    normalize_case(phrase)
  end

  def strip_punctuation(phrase)
    phrase.delete(":!&@$%^,.")
  end

  def normalize_case(phrase)
    phrase.downcase
  end
end

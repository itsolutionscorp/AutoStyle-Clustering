class Phrase
  attr_reader :contents

  def initialize(contents)
    contents = normalize(contents)
    @contents = sanitize(contents)
  end

  def word_count
    words_in(contents).each_with_object({}) do |word, counts|
      counts[word] ||= 0
      counts[word] += 1
    end
  end

  private

  def normalize(phrase)
    phrase.to_s.downcase
  end

  def sanitize(phrase)
    phrase.gsub(/[^[:alnum:]]+/, ' ')
  end

  def words_in(words)
    words.split
  end
end

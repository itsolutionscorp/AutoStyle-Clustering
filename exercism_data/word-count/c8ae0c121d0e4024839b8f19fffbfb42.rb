class Phrase
  ALLOWED_CHARACTERS = ['\w', '\s', ',', '\'']
  WORD_DELIMITERS = ['\s', ',']

  def initialize(words)
    @words = words
  end

  def word_count
    word_array.inject({}) do |counts, word|
      counts[word] ||= 0
      counts[word] += 1
      counts
    end
  end

  private

  def lowercase_sentence
    @words.downcase
  end

  def parsed_words
    lowercase_sentence.gsub(/[^#{ALLOWED_CHARACTERS.join}]/, '')
  end

  def word_array
    parsed_words.split(/[#{WORD_DELIMITERS.join('|')}]+/)
  end
end

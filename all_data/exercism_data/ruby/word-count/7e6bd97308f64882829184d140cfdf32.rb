class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    Hash[all_words.group_by(&:to_s).map { |word, array| [word, array.length] }]
  end

  private

  def all_words
    only_chars_we_care_about.split(' ')
  end

  def only_chars_we_care_about
    @words.downcase.gsub(/[^a-z1-9]/, ' ')
  end
end

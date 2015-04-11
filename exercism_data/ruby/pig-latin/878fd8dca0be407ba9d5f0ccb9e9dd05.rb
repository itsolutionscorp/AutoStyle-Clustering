# rubocop:disable Documentation
module PigLatin
  def self.translate(phrase)
    get_words(phrase)
      .map { |word| transform word }
      .join ' '
  end

  private

  def self.get_words(phrase)
    phrase.scan(/\w+/)
  end

  def self.transform(word)
    move_prefix_at_the_end(word) + 'ay'
  end

  def self.move_prefix_at_the_end(word)
    prefix = get_prefix(word)
    word[prefix.length..-1] + prefix
  end

  NO_PREFIX_PATTERN = /^([aeiou]|yt|xr)/
  SPECIAL_PREFIX_PATTERN = /^(s?ch|thr?|[^aeiou]?qu|[^aeiou])/

  def self.get_prefix(word)
    return '' if word =~ NO_PREFIX_PATTERN
    word[SPECIAL_PREFIX_PATTERN]
  end

  def self.vowel?(letter)
    'aeiou'.include? letter
  end
end

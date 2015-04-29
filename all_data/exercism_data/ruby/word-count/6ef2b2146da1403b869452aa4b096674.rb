class Phrase
  attr_reader :input

  def initialize(input)
    @input = preprocess(input)
  end

  def word_count
    group_words(input).reduce({}) { |memo, (k, v)| memo[k] = v.count; memo }
  end

  private
  # Massage an input into a stripped, downcased string with no characters
  # other than the ones in the whitelist (i.e. only alphanumerics and space).
  #
  def preprocess(input)
    input.
      to_s.
      strip.
      downcase.
      gsub(/[^#{character_class_whitelist}]/, '')
  end

  # A string representing a regex character class that matches only
  # the characters we care about.
  #
  def character_class_whitelist
    { letters: 'A-Za-z',
      numbers: '0-9',
      spaces:  '\ ' }.values.join
  end

  def group_words(str)
    str.split.sort.group_by(&:to_s)
  end
end

class PigLatin
  def self.translate phrase
    new(phrase).translation
  end

  def initialize phrase
    @phrase = phrase
  end

  def translation
    @phrase.gsub(/\w+/, &method(:translate))
  end

  STARTS_WITH_VOWELS = [
    /^[aeiou]/,
    /^[yx][^aeiou]/
  ]
  STARTS_WITH_CONSONANTS = [
    /^(?<consonants>[^aeiou]*qu)(?<rest>.*)$/,
    /^(?<consonants>[^aeiou]+)(?<rest>.*)$/
  ]

  private

  def translate word
    case word
    when *STARTS_WITH_VOWELS
      word + "ay"
    when *STARTS_WITH_CONSONANTS
      match = Regexp.last_match
      match[:rest] + match[:consonants] + "ay"
    else
      word
    end
  end
end

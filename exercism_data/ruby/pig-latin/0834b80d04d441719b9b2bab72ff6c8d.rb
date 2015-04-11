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

  def translate input
    case input
    when /^[aeiou]/,
         /^[yx][^aeiou]/
      input + "ay"
    when /^(?<start>[^aeiou]*qu)(?<rest>.*)$/,
         /^(?<start>[^aeiou]+)(?<rest>.*)$/
      match = Regexp.last_match
      match[:rest] + match[:start] + "ay"
    else
      input
    end
  end
end

class PigLatin

  def self.translate_word(english)
    if english.match /^[aeiou]|^yt|x[^aeiou]/
      english << "ay"
    elsif english.match /^sch|^thr/
      english[3..-1] + english[0..2] + "ay"
    elsif english.match /^[^aeiou]qu/
      english[3..-1] + english[0..2] + "ay"
    elsif english.match /^ch|^qu|^th/
      english[2..-1] + english[0..1] + "ay"
    else
      english[1..-1] + english[0] + "ay"
    end
  end

  def self.translate(english_phrase)
    english_phrase.split(" ").map { |word| self.translate_word(word) }.join(" ")
  end

  def self.split(english)
    []
  end
end

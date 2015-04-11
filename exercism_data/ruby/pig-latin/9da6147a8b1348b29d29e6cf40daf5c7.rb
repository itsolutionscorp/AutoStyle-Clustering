class PigLatin

  class << self
    def translate(phrase)
      phrase.to_s.split(" ").map do |word|
        word = move_consonant_sound(word) unless begins_with_vowel?(word)
        "#{word}ay"
      end.join(" ")
    end

    def begins_with_vowel?(word)
      %w(a e i o u).include? word[0]
    end

    private
    def move_consonant_sound(word)
      rules_for_first_sound = [
        "[^aeiou]qu", #consonant followed by "qu" sound'
        "thr",
        "sch",
        "th",
        "ch",
        "qu",
        "." #finally, any other characher
      ]
      word.gsub!(%r((#{rules_for_first_sound.join("|")})(.*)), '\2\1')
    end
  end
end

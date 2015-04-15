class PigLatin
  def self.translate(input)
    @@original = input

    paths.each do |condition, outcome|
      return outcome.call if condition
    end

  end

  private

  def self.original
    @@original
  end

  def self.paths
    { true                     => lambda {consonant_translation},
      starts_with_vowel_sound? => lambda {vowel_translation},
      is_blend?(two)           => lambda {short_blend_translation},
      is_blend?(three)         => lambda {long_blend_translation},
      multiple_word_phrase?    => lambda {translate_phrase(original)}
    }
  end



  def self.one
    original[0]
  end

  def self.two
    original[0,2]
  end

  def self.three
    original[0,3]
  end

  def self.consonant_translation
    original[1..-1] + one + "ay"
  end



  def self.starts_with_vowel_sound?
    vowel_starts.any? { |v| [one, two].include?(v) }
  end

  def self.vowel_translation
    original + "ay"
  end



  def self.is_blend?(letters)
    blends.any? { |b| b == letters }
  end

  def self.short_blend_translation
    original[2..-1] + two + "ay"
  end

  def self.long_blend_translation
    original[3..-1] + three + "ay"
  end



  def self.multiple_word_phrase?
    original.split.length > 1
  end

  def self.translate_phrase(phrase)
    phrase.split.collect do |w|
      translate(w)
    end.join(" ")
  end



  def self.vowel_starts
    ["a", "e", "i", "o", "u", "xr", "yt"]
  end

  def self.blends
    ["ch", "qu", "squ", "th", "thr", "sch"]
  end

end

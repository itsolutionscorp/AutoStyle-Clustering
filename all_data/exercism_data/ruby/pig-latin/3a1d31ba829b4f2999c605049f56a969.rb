class PigLatin
  def self.translate(input)
    new(input).translate
  end

  attr_reader :original

  def initialize(input)
    @original = input
  end

  def translate
    paths.each do |condition, outcome|
      return outcome.call if condition
    end
  end

  private

  def paths
    { true                     => lambda {consonant_translation},
      starts_with_vowel_sound? => lambda {vowel_translation},
      is_blend?(two)           => lambda {short_blend_translation},
      is_blend?(three)         => lambda {long_blend_translation},
      multiple_word_phrase?    => lambda {translate_phrase(original)}
    }
  end

  def one
    original[0]
  end

  def two
    original[0,2]
  end

  def three
    original[0,3]
  end

  def consonant_translation
    original[1..-1] + one + "ay"
  end



  def starts_with_vowel_sound?
    vowel_starts.any? { |v| [one, two].include?(v) }
  end

  def vowel_translation
    original + "ay"
  end



  def is_blend?(letters)
    blends.any? { |b| b == letters }
  end

  def short_blend_translation
    original[2..-1] + two + "ay"
  end

  def long_blend_translation
    original[3..-1] + three + "ay"
  end



  def multiple_word_phrase?
    original.split.length > 1
  end

  def translate_phrase(phrase)
    phrase.split.collect do |w|
      PigLatin.translate(w)
    end.join(" ")
  end



  def vowel_starts
    ["a", "e", "i", "o", "u", "xr", "yt"]
  end

  def blends
    ["ch", "qu", "squ", "th", "thr", "sch"]
  end

end

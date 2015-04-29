class PigLatin
  class << self
    def translate(sentence)
      sentence.split(' ').map do |word|
        translate_word(word)
      end.join(' ')
    end

    def translate_word(word)
      result = if starts_with_vowel?(word)
        word
      else
        move_consonant_to_end(word)
      end

      result + "ay"
    end

    def starts_with_vowel?(word)
      word.match(/^(#{vowel_groups_regexes})/)
    end

    def vowel_groups_regexes
      %w(
        yt
        xr
        [aeiou]
      ).join('|')
    end

    def move_consonant_to_end(word)
      word.gsub(/^(#{consonant_groups_regexes})(.+)/, '\2\1')
    end

    def consonant_groups_regexes
      %w(
        sch
        thr
        th
        .?qu
        ch
        [^aeiou]
      ).join('|')
    end
  end
end

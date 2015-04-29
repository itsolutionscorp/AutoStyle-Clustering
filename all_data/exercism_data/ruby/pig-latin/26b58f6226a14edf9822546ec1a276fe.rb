class PigLatin
  def self.translate(phrase)
    words_characters = []
    pig_latin_word   = ""
    pig_latin_phrase = ""
    phrase.split(' ').each { |word| words_characters << word.chars }
    words_characters.each do |word|
      case word.join
      when /^([aeiou]|(yt)|(xr))/
        pig_latin_word = word.join + "ay"
      when /^(([^aeiou]qu)|(thr)|(sch))/
        first_letters = word[0..2].join
        word.shift(3)
        word.push(first_letters + "ay")
        pig_latin_word = word.join
      when /^((ch)|(qu)|(th))/
        first_letters = word[0..1].join
        word.shift(2)
        word.push(first_letters + "ay")
        pig_latin_word = word.join
      when /^[bcdfghjklmnpqrstvwxyz]/
        first_letter = word[0]
        word.shift
        word.push(first_letter + "ay")
        pig_latin_word = word.join
      end

      if pig_latin_phrase.empty?
        pig_latin_phrase += pig_latin_word
      else
        pig_latin_phrase += " " + pig_latin_word
      end
    end
    return pig_latin_phrase
  end
end

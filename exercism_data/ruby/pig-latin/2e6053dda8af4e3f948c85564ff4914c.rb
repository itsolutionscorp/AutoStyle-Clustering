class PigLatin

  def self.translate(string)
    string.split.inject([]){|sum, word| sum << translate_word(word)}.join(" ")
  end
  
  private

  def self.translate_word(word)
    if word.match(/^[aeiou]|^yt|^xr/)
      word + "ay"
    else
      rotate_until_vowel(word) + "ay"
    end
  end

  def self.rotate_until_vowel(word)
    raise "No vowel in word" unless word.match(/[aeiou]/)

    rotation = 1
    while !word.match(/^[aeiou]/)
      rotation = 2 if word.start_with?("qu")

      word = word.chars.rotate(rotation).join
      rotation = 1
    end
    word
  end
end

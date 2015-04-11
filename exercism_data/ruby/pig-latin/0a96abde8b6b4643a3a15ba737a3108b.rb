class PigLatin

  VOWELS = %w[xr a e i o u yt]
  CONSONANTS = %w[x y ch qu squ thr th sch].sort.reverse

  def self.translate(phrase)
    answer = []
    phrase.split(' ').each do |word|
      answer << get_beginning_sound(word)
    end
    answer.join(' ')
  end

  def self.piggify_vowel_sound(word)
    word + "ay"
  end

  def self.piggify_consonant_sound(word , length = 1)
    pig_word = []
    pig_word << word.chars[length..-1] << word.chars[0,length] << ["ay"]
    pig_word.join
  end

  def self.get_beginning_sound(word)
    VOWELS.each do |vow|
      place = word =~ /#{vow}/
      if place == 0
        return piggify_vowel_sound(word)
      end
    end
    
    CONSONANTS.each do |con|
      place = word =~ /#{con}/
      if place == 0
        return piggify_consonant_sound(word,con.length)
      end
    end

    piggify_consonant_sound(word,1)

  end
end

module PigLatin  
  def self.translate(in_string)
    wordlist_from(in_string).map{ |word| translate_word(word) }.join(" ")
  end

  private

  def self.wordlist_from(in_string)
    in_string.downcase.scan(/\w+/)
  end

  EXCEPTIONS = ["xr", "yt"]

  def self.translate_word(word)
    EXCEPTIONS.each { |ex| return(word + "ay") if word.start_with?(ex) }

    pig(word)
  end

  def self.pig(word)
    /(?<consonant>s?qu|[^aeiou]+)?(?<remainder>.+)/ =~ word
    "#{remainder}#{consonant}ay"
  end
end

class PigLatin
  def self.translate phrase
    PigLatin.new(phrase).to_s
  end
  
  def initialize plaintext
    @plaintext = plaintext.to_s
  end
  
  def to_s
    plaintext.gsub(/\w+/){|word| latin word }
  end
  
  private
  def latin word
    split_word = word.scan /^([^aeiou]?qu|s?ch|thr?|[^aeiouxy]|x(?!r)|y(?!t))?(.+)/
    if split_word[0][0]
      "#{split_word[0][1]}#{split_word[0][0]}ay"
    else
      "#{word}ay"
    end
  end
  
  def plaintext
    @plaintext
  end
end

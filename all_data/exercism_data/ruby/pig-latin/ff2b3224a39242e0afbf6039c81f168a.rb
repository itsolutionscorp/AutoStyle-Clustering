class PigLatin

  def self.translate(input)
    input.split.map(&method(:translate_word)).join(' ')
  end

  private

  def self.translate_word(word)
    word.match(/(^[^[aeiouyqyx]]+|^y|^x|^s?qu)([aeiou])(\w+)$/)
    $1.nil? ? word + "ay" :  "#{$2}#{$3}#{$1}ay"
  end
end

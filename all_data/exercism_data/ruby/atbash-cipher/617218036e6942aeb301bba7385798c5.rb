module Atbash
  LETTERS = %w(a b c d e f g h i j k l m n o p q r s t u v w x y z)
  LETTERS_LENGTH = LETTERS.length
  SPACE_INTERVAL = 5
  SPACE_REGEXP = Regexp.new("\\w{1,#{SPACE_INTERVAL}}")

  def self.encode(in_string)
    chars_from(in_string).map { |char|
      encode_char(char)
    }.join.scan(SPACE_REGEXP).join(" ")
  end

  private

  @@cipher = Hash.new { |cipher, character| character }

  LETTERS.each_with_index do |character, i|
    @@cipher[character] = LETTERS[LETTERS_LENGTH - i - 1]
  end

  def self.encode_char(character)
    @@cipher[character]
  end

  def self.chars_from(in_string)
    in_string.downcase.scan(/\w/)
  end
end

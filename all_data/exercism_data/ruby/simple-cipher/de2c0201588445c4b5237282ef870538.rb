class Cipher
  LETTERS = %w(a b c d e f g h i j k l m n o p q r s t u v w x y z)

  attr_reader :key

  def self.generate_key(len = 100)
    len.times.map{ |key| LETTERS[(rand*26).floor] }.join
  end

  def initialize(new_key)
    fail ArgumentError.new("Invalid Key") unless valid_key? new_key

    @key = new_key || Cipher.generate_key
  end

  def encode(plain_text)
    transcode(plain_text, &:+)
  end

  def decode(code)
    transcode(code, &:-)
  end

  private

  def valid_key?(new_key)
    !new_key || !!new_key.match(/\A[a-z]+\Z/)
  end

  def get_letters(plain_text)
    plain_text.downcase.scan /[a-z]/
  end

  def transcode(text, &direction)
    output = ""
    get_letters(text).each_with_index do |letter, i|
      output << translate(letter, key[i % key.length], &direction)
    end
    output
  end

  def translate(letter, key_char)
    LETTERS[yield(letter.ord - "a".ord, key_char.ord - "a".ord) % 26]
  end
end

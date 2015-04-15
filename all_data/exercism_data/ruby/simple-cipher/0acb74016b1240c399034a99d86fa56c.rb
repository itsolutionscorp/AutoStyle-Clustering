class Cipher
  LETTERS = "abcdefghijklmnopqrstuvwxyz"

  attr_reader :key

  def self.generate_key(len=100)
    len.times.reduce("") do |key|
      key << LETTERS[(rand*26).floor]
    end
  end

  def initialize(new_key)
    new_key ||= Cipher.generate_key
    raise ArgumentError.new("Invalid Key") unless valid_key? new_key
    @key = new_key
  end

  def encode(plain_text)
    transcode(plain_text, &:+)
  end

  def decode(code)
    transcode(code, &:-)
  end

  private

  def valid_key?(new_key)
    !!new_key.match(/\A[a-z]+\Z/)
  end

  def get_letters(plain_text)
    plain_text.downcase.chars.select do |c|
      c.match /[a-z]/
    end
  end

  def transcode(text, &direction)
    output = ""
    get_letters(text).each_with_index do |letter, i|
      output << translate(letter, @key[i], &direction)
    end
    output
  end

  def translate(letter, key_char, &direction)
    LETTERS[yield(letter.ord - "a".ord, key_char.ord - "a".ord) % 26]
  end

end

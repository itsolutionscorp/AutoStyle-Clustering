class Cipher
  VALUE_OF_A = 'a'.ord

  attr_reader :key

  def initialize(key = nil)
    key ||= 'a' * 10
    raise ArgumentError if key == "" || key.match(/[A-Z0-9]/)
    @key = key
  end

  def encode(message)
    process_message(message, :encode_char)
  end

  def decode(message)
    process_message(message, :decode_char)
  end

  def process_message(message, char_method)
    message.chars.each.with_index.with_object("") do |(char, i), new_message|
      new_message << public_send(char_method, char, i)
    end
  end

  def encode_char(char, i)
    process_char(char, i, :+)
  end

  def decode_char(char, i)
    process_char(char, i, :-)
  end

  def process_char(char, i, method)
    new_char_value = char_diff(char).send(method, char_diff(key[i]))
    new_char_value %= 26
    new_char_value += VALUE_OF_A
    new_char_value.chr
  end

  def char_diff(c)
   c.ord - VALUE_OF_A
  end
end

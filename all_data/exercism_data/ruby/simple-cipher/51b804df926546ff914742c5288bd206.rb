class Cipher
  ALPHABET = ('a'..'z').to_a

  attr_reader :key
  def initialize(key)
    @key = key || randomkey
    validate_key
  end

  def encode(plaintext)
    transform(plaintext, :+)
  end

  def decode(cipher)
    transform(cipher, :-)
  end

private
  def transform(text, action)
    #kcgenerator = key_char_generator
    #text.chars.map {|ch| transform_character(ch, kcgenerator, action) }.join
    kcgenerator = key_char_generator
    text.chars.map do |ch|
      transform_character(ch, kcgenerator.next, action)
    end.join
  end

  def key_char_generator
    #Fiber.new {key.chars.cycle {|k| Fiber.yield k }}
    key.chars.lazy.map {|k| k}.cycle
  end

  def transform_character(ch, key_char, action)
    ALPHABET[shift_index(ch, key_char, &action)]
  end

  def shift_index(ch, k, &action)
    action.call(position(ch), position(k)) % ALPHABET.size
  end

  def position(ch)
    ALPHABET.index ch
  end

  def randomkey
    100.times.map {ALPHABET.sample}.join
  end

  def validate_key
    raise ArgumentError if key.match /\A[^a-z]*\z/
  end
end

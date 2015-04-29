class Cipher
  attr_reader :key
    
  ALPHABET = ('a'..'z').to_a

  def initialize(*args)
    @key = args.first || (0..99).map{('a'..'z').to_a[rand(26)]}.join
    validate_key
  end

  def encode(string)
    convert(string,:encode)
  end

  def decode(cipher)
    convert(cipher,:decode)
  end

  private

  def validate_key
    raise ArgumentError unless key.match(/[a-z]+/)
  end

  def value(char)
    ALPHABET.index(char)
  end

  def process_char(char, key_char, process)
    direction = case process
               when :encode then 1
               when :decode then -1
               end

    ALPHABET.rotate(direction * value(key_char))[value(char)]
  end

  def convert(text, process)
    text.chars.map.with_index {|char,i| process_char(char,key[i],process)}.join
  end

end

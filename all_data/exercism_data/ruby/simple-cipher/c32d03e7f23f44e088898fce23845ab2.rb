class Cipher
  attr_reader :key

  def initialize(key = default_key)
    validate_key(key)
    @key = key
  end

  def default_key
    "dddddddddd"
  end

  def encode(string)
    convert_string(string, :encode_char)
  end

  def decode(string)
    convert_string(string, :decode_char)
  end

  private

  def validate_key(key)
    raise ArgumentError unless key.match(/^[a-z]+$/)
  end

  def convert_string(string, method)
    string.bytes.each_with_index.map do |char, index|
      wrap_char(self.send(method, char, index))
    end.map(&:chr).join
  end

  def encode_char(char, index)
    char + key_offsets[index]
  end

  def decode_char(char, index)
    char - key_offsets[index]
  end

  def wrap_char(char)
    (char - 'a'.ord) % ('a'..'z').to_a.length + 'a'.ord
  end

  def key_offsets
    @key_offsets ||= key.bytes.map { |c| c - 'a'.ord }
  end
end

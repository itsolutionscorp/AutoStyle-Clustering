class Cipher
  attr_reader :key
  def initialize key = nil, keylength=100
    if key
      raise ArgumentError unless key.match /[a-z]+/
      @key = key
    else
      @key = to_string Array.new(keylength){ Random.rand(0...26) }
    end
  end
  
  def encode phrase
    to_string to_bytes(phrase).map.with_index{|c,i| encode_value c, i}
  end
  
  def decode phrase
    to_string to_bytes(phrase).map.with_index{|c,i| decode_value c, i}
  end
  
  private
  
  def encode_value value, key_index
    (value + key_bytes[key_index % key_bytes.length]) % 26
  end
  
  def decode_value value, key_index
    (value - key_bytes[key_index % key_bytes.length]) % 26
  end
  
  def key_bytes
    @key_bytes ||= to_bytes key
  end
  
  def to_bytes string
    string.unpack("c*").map{|c| c - 97}
  end
  
  def to_string bytes
    bytes.map{|c| c + 97}.pack("c*")
  end
end

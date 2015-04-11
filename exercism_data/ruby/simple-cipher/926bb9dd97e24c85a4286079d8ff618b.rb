class Cipher
  ALPHABET = ("a".."z").to_a

  attr_reader :key

  def initialize(key=nil)
    if key
      raise ArgumentError unless valid?(key)
      @key = key
    else
      @key = 'aaaaaaaaaa'
    end
  end

  def encode(message)
    process(message, :encode)
  end

  def decode(message)
    process(message, :decode)
  end

  private

  def valid?(key)
    key.match(/[a-z]+/)
  end

  def process(message, direction)
    message.chars.map.with_index do |c, i|
      distance = key_distances[i]
      rotated = rotate(direction, distance)
      rotated[c]
    end.join
  end

  def key_distances
    @distances ||= key.chars.map{|c| calculate_distance(c)}
  end

  def calculate_distance(char)
    char.getbyte(0) - "a".getbyte(0)
  end

  def rotate(direction, distance)
    if direction == :encode 
      rotate_characters(distance)
    else
      rotate_characters(-distance)
    end
  end

  def rotate_characters(n)
    rotated = ALPHABET.rotate(n)
    Hash[ALPHABET.zip(rotated)]
  end

end

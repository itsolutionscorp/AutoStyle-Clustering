class Cipher
  attr_reader :key

  LETTERS = [*'a'..'z']
  DEFAULT_KEY = 'aaaaaaaaaa'

  def initialize(key = DEFAULT_KEY)
    @key = key
    ensure_valid_key
  end

  def encode(msg)
    shift(msg, 1)
  end

  def decode(msg)
    shift(msg, -1)
  end

  def shift(msg, direction)
     msg.chars.each_with_index.map do |char, i|
      increment = LETTERS.index(key[i])
      shift_letter(char, increment * direction)
    end.join
  end

  def shift_letter(char, increment)
    old_idx = LETTERS.index(char)
    new_idx = (old_idx + increment) % 26
    LETTERS[new_idx]
  end

  private

  def ensure_valid_key
    raise ArgumentError if @key.empty? || @key[/[A-Z\d]/] 
  end
end

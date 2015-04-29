class Cipher
  ALPHABET = ('a'..'z').to_a

  def initialize key=create_key
    raise ArgumentError unless key =~ /\A[a-z]+\z/
    @key = key
  end

  attr_reader :key

  def encode message
    recode(message) do |plain, shift|
      cipher = plain + shift
    end
  end

  def decode message
    recode(message) do |cipher, shift|
      plain = cipher - shift
    end
  end

  def recode message
    message.chars.zip(key.chars.cycle).map do |message_char, shift_char|
      message_index = ALPHABET.index(message_char)
      shift_index   = ALPHABET.index(shift_char)

      recoded_index = yield(message_index,shift_index)

      ALPHABET[recoded_index % ALPHABET.length]
    end.join
  end

  def create_key
    100.times.map { ALPHABET.sample }.join
  end
end

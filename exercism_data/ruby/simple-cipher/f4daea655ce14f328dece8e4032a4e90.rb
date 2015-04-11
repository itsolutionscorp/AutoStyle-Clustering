class Cipher
  attr_reader :key

  def initialize(key)
    validate_key!(key) if key
    @key = key || generate_key
  end

  def encode(plaintext)
    transliterate(plaintext) do |from, to|
      alphabet.sum(from, to)
    end
  end

  def decode(encrypted)
    transliterate(encrypted) do |from, to|
      alphabet.diff(from, to)
    end
  end

  private

  def validate_key!(key)
    unless key =~ /\A[a-z]+\z/
      raise ArgumentError, "invalid key #{key.inspect}"
    end
  end

  def generate_key
    alphabet.random_string(100)
  end

  def transliterate(from, &block)
    from.chars.zip(key.chars.cycle).map(&block).join
  end

  def alphabet
    @alphabet ||= Alphabet.new("a" .. "z")
  end

  class Alphabet
    attr_reader :letters
    private :letters

    def initialize(letters)
      @letters = letters.to_a
    end

    def sum(a, b)
      calc(a, :+, b)
    end

    def diff(a, b)
      calc(a, :-, b)
    end

    def random_string(length)
      amount = length / letters.size
      (letters * amount).shuffle.first(length).join
    end

    private

    def size
      letters.size
    end

    def at(index)
      letters.at(index)
    end

    def index(letter)
      letters.index(letter)
    end

    # Still don't like it.
    # Should I introduce a letter class having this code:
    #   alphabet.letter("a") + alphabet.letter("b")
    # and
    #   alphabet.letter("a") - alphabet.letter("b")
    def calc(a, op, b)
      diff = (index(a).send(op, index(b))) % size
      at(diff)
    end
  end
end

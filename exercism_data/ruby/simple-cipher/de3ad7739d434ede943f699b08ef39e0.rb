class Cipher
  attr_reader :key

  def initialize(key)
    validate_key!(key) if key
    @key = key || generate_key
  end

  def encode(plaintext)
    transliterate(plaintext) do |from, to|
      alphabet[from] + alphabet[to]
    end
  end

  def decode(encrypted)
    transliterate(encrypted) do |from, to|
      alphabet[from] - alphabet[to]
    end
  end

  private

  def validate_key!(key)
    unless key =~ /\A[a-z]+\z/
      raise ArgumentError, "invalid key #{key.inspect}"
    end
  end

  def generate_key
    alphabet.random(100).join
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

    def initialize(graphemes)
      @letters = graphemes.map.with_index { |grapheme, index| Letter.new(self, grapheme, index) }
    end

    def [](arg)
      case arg
      when String
        letters.find { |letter| letter.is?(arg) }
      when Fixnum
        letters[arg % size]
      end
    end

    def random(length)
      length.times.map { letters.sample }
    end

    private

    def size
      letters.size
    end
  end

  Letter = Struct.new(:alphabet, :grapheme, :index) do
    def +(other)
      alphabet[self.index + other.index]
    end

    def -(other)
      alphabet[self.index - other.index]
    end

    def is?(grapheme)
      self.grapheme == grapheme
    end

    alias :to_s :grapheme
  end
end

class Atbash
  class EncodedChar
    ALPHABETH = ('a'..'z').to_a.reverse

    attr_reader :char, :index

    def initialize(char, index)
      @char = char
      @index = index
    end

    def encode
      [prefix, new_char].join
    end

    private

    def prefix
      index > 0 && index % 5 == 0 ? ' ' : ''
    end

    def new_char
      return char if number?
      ALPHABETH[char.ord - 'a'.ord]
    end

    def number?
      char =~ /\d/
    end
  end

  def initialize(string)
    @string = string
  end

  def self.encode(string)
    new(string).encode
  end

  def encode
    cleaned_chars.map.with_index do |char, i|
      EncodedChar.new(char, i).encode
    end.join
  end

  private

  def cleaned_chars
    @string.downcase.gsub(/[^a-z0-9]/, '').chars.to_a
  end
end

module BaseX
  attr_reader :str

  def initialize(str)
    self.str = str
  end

  def str=(other)
    @str = other.downcase
    @str = '0' unless valid?
  end

  def to_decimal
    self.class.decimal_map[str]
  end

  def valid?
    self.class::VALID.match(str)
  end

  module ClassMethods
    def from_decimal(decimal)
      decimal = decimal.to_i
      return decimal_map.invert[decimal] if decimal_map.value?(decimal)
      return '' if decimal.zero?
      whole, remainder = decimal.divmod(self::BASE)
      base = from_decimal(whole) + digits[remainder]
      decimal_map[base] = decimal
      base
    end

    def decimal_map
      @decimal_map ||= Hash.new do |hash, key|
        hash[key] = key.reverse.chars.each_with_index.reduce(0) do |acc, (digit, index)|
          acc + self::TO_DEC[digit] * self::BASE**index
        end
      end
    end
  end

  def self.included(base)
    base.const_set(:TO_DEC, Hash[base.digits.each_with_index.to_a])
    base.const_set(:BASE, base.digits.length)
    base.const_set(:VALID, Regexp.new("^[#{base.digits.join}]*$"))
    base.extend(BaseX::ClassMethods)
  end
end

class Binary
  def self.digits
    %w(0 1)
  end

  include BaseX
end

class SecretHandshake
  attr_reader :code, :commands

  COMMANDS = [
    ->(c) { c << 'wink' },
    ->(c) { c << 'double blink' },
    ->(c) { c << 'close your eyes' },
    ->(c) { c << 'jump' },
    ->(c) { c.reverse! }
  ]

  def initialize(code)
    self.code = code
  end

  def code=(other)
    @code = Binary.from_decimal(other).chars
    @commands = nil
  end

  def commands
    @commands ||= generate_commands
  end

  private

  def generate_commands
    code.reverse.each_with_index.with_object([]) do |(flag, index), commands|
      COMMANDS[index].call(commands) if flag == '1'
    end
  end
end

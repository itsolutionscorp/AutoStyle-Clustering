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
    self.class.instance_variable_get('@decimal_map')[str]
  end

  def valid?
    self.class::VALID.match(str)
  end

  def self.included(base)
    base.const_set(:TO_DEC, Hash[base.digits.each_with_index.to_a])
    base.const_set(:BASE, base.digits.length)
    base.const_set(:VALID, Regexp.new("^[#{base.digits.join}]*$"))
    base.class_exec do
      @decimal_map = Hash.new do |hash, key|
        hash[key] = key.reverse.chars.each_with_index.reduce(0) do |acc, (digit, index)|
          acc + self::TO_DEC[digit] * self::BASE**index
        end
      end
    end
  end
end

class Binary
  def self.digits
    %w(0 1)
  end

  include BaseX
end

class Hexadecimal
  def self.digits
    %w(0 1 2 3 4 5 6 7 8 9 a b c d e f)
  end

  include BaseX
end

class Trinary
  def self.digits
    %w(0 1 2)
  end

  include BaseX
end

require 'delegate'

class Say
  attr_reader :nb
  def initialize nb
    @nb = nb
  end

  def in_english
    nb.in_english
  end
end

class Fixnum
  def in_english
    raise ArgumentError unless (0..999999999999).cover? self
    Number.anglicize(self)
  end

  private

  class Number

    def self.anglicize(nb)
      return 'zero' if nb.zero?
      BigNumber.new(nb.to_s).in_english
    end

    def in_english
      return right.in_english if left.zero?
      [left.in_english, keyword, right.in_english].compact.join(' ')
    end

    protected

    attr_accessor :data

    def initialize(nb=nil)
      @data = nb
    end

    def zero?
      @data.to_i.zero?
    end

    def left
      length  = data[0..-(tail.size + 1)].length
      Hundred.new(data.chars.first(length).join)
    end

    def keyword
      self.class.name.split('::').last.downcase
    end

    def right
      tail.type.new(data.chars.last(tail.size).join)
    end
  end

  class Decimal < Number
    UNITS = {
      '1' => 'one',
      '2' => 'two',
      '3' => 'three',
      '4' => 'four',
      '5' => 'five',
      '6' => 'six',
      '7' => 'seven',
      '8' => 'eight',
      '9' => 'nine'
    }

    SPECIALS = {
      '11' => 'eleven',
      '12' => 'twelve',
      '13' => 'thirteen',
      '14' => 'fourteen',
      '15' => 'fifteen',
      '16' => 'sixteen',
      '17' => 'seventeen',
      '18' => 'eighteen',
      '19' => 'nineteen'
    }

    DECIMALS = {
      '10' => 'ten',
      '20' => 'twenty',
      '30' => 'thirty',
      '40' => 'forty',
      '50' => 'fifty',
      '60' => 'sixty',
      '70' => 'seventy',
      '80' => 'eighty',
      '90' => 'ninety'
    }

    def in_english
      SPECIALS[data] || UNITS[data] || DECIMALS[data] || split
    end

    private

    def split
      decimal, unit = data.chars
      suffix = UNITS[unit]
      prefix = DECIMALS[decimal + '0'] if decimal
      if prefix
        [prefix, '-', suffix.to_s].join
      elsif suffix
        suffix
      end
    end
  end

  class Hundred < Number
    def tail
      Tail.new(2, Decimal)
    end
  end

  class Thousand < Number
    def tail
      Tail.new(3, Hundred)
    end
  end

  class Million < Number
    def tail
      Tail.new(6, Thousand)
    end
  end

  class Billion < Number
    def tail
      Tail.new(9, Million)
    end
  end

  class BigNumber < Struct.new(:data)
    def in_english
      chars = data.chars
      prefix = Billion.new(chars[0..-13].join)
      suffix = Billion.new(chars.last(12).join)
      [prefix, suffix].map(&:in_english).compact.join(' ')
    end
  end

  class Tail < Struct.new(:size, :type); end
end

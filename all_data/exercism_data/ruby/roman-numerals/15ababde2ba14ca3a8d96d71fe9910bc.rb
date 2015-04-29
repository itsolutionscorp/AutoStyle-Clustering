class Fixnum
  class Roman
    def initialize(value, letter)
      @value = value
      @letter = letter
    end
    attr_reader :value
    attr_reader :letter
  end

  def self.init_roman_numbers()
    basic_roman_numbers = [ Roman.new(1, 'I'),
                            Roman.new(5, 'V'),
                            Roman.new(10, 'X'),
                            Roman.new(50, 'L'),
                            Roman.new(100, 'C'),
                            Roman.new(500, 'D'),
                            Roman.new(1000, 'M') ]
    result = []
    substracted = nil
    basic_roman_numbers.each do |basic|
      result.unshift(Roman.new(basic.value - substracted.value, substracted.letter + basic.letter)) if substracted
      result.unshift(basic)
      substracted = basic if basic.value.to_s[0] == '1'
    end
    result
  end

  ROMAN_NUMBERS = self.init_roman_numbers()

  def to_roman
    n = self
    result = ''
    ROMAN_NUMBERS.each do |digit|
      while n >= digit.value do
        result += digit.letter
        n -= digit.value
      end
    end
    result
  end
end

module Liter
  LOWER = ('!'..'~').grep /[[:lower:]]/
  UPPER = ('!'..'~').grep /[[:upper:]]/
  LITERS = LOWER + UPPER
  NUMERATOR = LITERS.size
  NAMER_NUMERATOR = 1000
  class Number
    attr_reader :value
    attr_reader :liter
    def initialize(cifer_number)
      @value = cifer_number
      @liter = Liter.convert(cifer_number)
    end
    #basic operations
    [:+, :-, :*, :/].each do |operation|
      define_method operation do |other|
        self.class.new(@value.send(operation, other.value))
      end
    end
    #comparison operations
    [:==, :<=>].each do |operation|
      define_method operation do |other|
        @value.send(operation, other.value)
      end
    end
    def inc
      @value+=1
      @liter=Liter.convert(@value)
      self
    end
    def to_s
      @liter
    end
  end

  def self.convert(cifer_number)
    self.recursive_convert(cifer_number, [])
  end

  def self.name(cifer)
    if cifer/NAMER_NUMERATOR < NUMERATOR
      zero_alpha = Number.new(0).to_s
      first_alpha = Number.new(cifer/NAMER_NUMERATOR).to_s
      digit_part = sprintf("%03d",(cifer%NAMER_NUMERATOR)).to_s
      [zero_alpha, first_alpha, digit_part].join ''
    else
      alpha_part = Number.new(cifer/NAMER_NUMERATOR).to_s
      digit_part = sprintf("%03d",(cifer%NAMER_NUMERATOR))
      [alpha_part, digit_part].join ''
    end
  end

  def self.recursive_convert(cifer_number, acc)
    if cifer_number >= NUMERATOR
      self.recursive_convert(cifer_number/NUMERATOR, acc.unshift(LITERS[cifer_number%NUMERATOR]))
    else
      acc.unshift LITERS[cifer_number]
      acc.join ''
    end
  end

end

class Robot
  attr_reader :name
  @@counter = 0
  def initialize
    reset
  end
  def reset
    @@counter +=1
    @name = Liter.name(@@counter)
  end
end

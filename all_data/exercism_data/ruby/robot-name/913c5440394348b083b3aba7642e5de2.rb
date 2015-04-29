module SerialNumber
  def generate
    chars  = (65..90).map(&:chr)
    digits = (0..9).to_a

    [chars.sample(2), digits.sample(3)].join
  end

  module_function :generate
end

require 'set'

class Robot
  attr_reader :name

  def initialize
    @@serial_numbers ||= Set.new

    reset
  end

  def reset
    @name = unique_serial_number
  end

  private

  def unique_serial_number
    serial_number = SerialNumber.generate

    if @@serial_numbers.add?(serial_number)
      serial_number
    else
      unique_serial_number
    end
  end
end

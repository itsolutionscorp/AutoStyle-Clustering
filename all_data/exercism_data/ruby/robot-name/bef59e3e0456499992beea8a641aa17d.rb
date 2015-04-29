class SerialCode
  SerialCodeOverflowError = Class.new(StandardError)

  def initialize
    @prefix = 'AA'
    @serial_number = '000'
  end

  def generate
    name = @prefix + @serial_number
    fail SerialCodeOverflowError, "Maximum serials created" if name == 'ZZ999'

    @prefix.next! if @serial_number == '999'
    @serial_number.next!
    
    name
  end
end

class Robot
  attr_reader :name

  class << self; attr_reader :serial_generator end
  @serial_generator = SerialCode.new

  def initialize
    @name = Robot.serial_generator.generate
  end

  def reset
    @name = Robot.serial_generator.generate
  end
end

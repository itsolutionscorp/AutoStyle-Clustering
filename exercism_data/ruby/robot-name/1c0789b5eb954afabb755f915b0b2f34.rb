class Robot
  attr_reader :name
  
  def reset
    new_name!
  end

  alias_method :initialize, :reset

  private

  def new_name!
    @name = SerialNumberFactory.generate
  end
end

class SerialNumberFactory
  class << self
    def generate
      serial_number(*serials.next)
    end

    private

    def serials
      @serials ||= letters.zip(numbers)
    end

    def serial_number(prefix, number)
      "#{prefix}#{number.to_s.rjust(3, "0")}"
    end

    def letters
      ("AA".."ZZ").lazy.cycle 
    end

    def numbers
      (1..Float::INFINITY).lazy
    end
  end

end

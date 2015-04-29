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

    # use a prime number of letter combinations for
    # 672327 non-repeating serial numbers
    def letters
      ("AA".."ZZ").first(673).lazy.cycle 
    end

    def numbers
      (1..999).lazy.cycle
    end
  end

end

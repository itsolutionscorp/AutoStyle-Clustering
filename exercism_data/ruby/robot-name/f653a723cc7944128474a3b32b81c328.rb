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
      "#{prefix}#{number}"
    rescue StopIteration
      next_prefix!
      numbers.rewind
      retry
    end

    private

    def prefixes
      @prefixes ||= ("AA".."ZZ").to_enum
    end

    def numbers
      @numbers ||= ("000".."999").to_enum
    end

    def prefix
      @prefix || next_prefix!
    end

    def number
      numbers.next
    end

    def next_prefix!
      @prefix = prefixes.next
    rescue StopIteration
      raise StandardError.new("Unique serials exhausted.")
    end
  end

end

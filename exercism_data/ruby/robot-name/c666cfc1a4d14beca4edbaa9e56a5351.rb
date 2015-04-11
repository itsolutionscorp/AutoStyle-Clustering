module RobotName
  class << self
    def next_name
      @serial_number = serial_number + 1

      if (serial_number >= 1000)
        @serial_number = 1
        @series = series.next
      end

      "#{series_to_s}#{serial_number_to_s}"
    end

    def serial_number
      @serial_number || 0
    end

    def series
      @series || 'AA'
    end

    def series_to_s
      series
    end

    def serial_number_to_s
      "%03d" % serial_number
    end
  end
end

class Robot
  attr_reader :name, :name_factory

  def initialize(name_factory=RobotName)
    @name_factory = name_factory
    reset_name!
  end

  def reset
    reset_name!
  end

  private

  def reset_name!
    @name = name_factory.next_name
  end
end

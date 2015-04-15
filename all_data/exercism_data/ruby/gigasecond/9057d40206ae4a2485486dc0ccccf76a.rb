class Gigasecond
  ONE_GIGASECOND = (10**9)

  def self.from(date)
    date_in_seconds = ConvertDate.new(date).to_seconds
    ConvertSeconds.new(date_in_seconds + ONE_GIGASECOND).to_date
  end

  class ConvertDate
    def initialize(date)
      @date = date
    end

    def to_seconds
      @date.strftime("%s").to_i
    end
  end

  class ConvertSeconds
    def initialize(seconds)
      @seconds = seconds
    end

    def to_date
      DateTime.strptime(@seconds.to_s, "%s").to_date
    end
  end
end

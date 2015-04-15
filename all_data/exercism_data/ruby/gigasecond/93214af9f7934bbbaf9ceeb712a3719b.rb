class Gigasecond
  
  attr_reader :birthday
  SECONDS_IN_DAY = 60 * 60 * 24
  GIGASECOND = 10**9

  def initialize(birthday)
    @birthday = birthday
  end

  def date
    birthday + gigaseconds_per_day
  end

  private

    def gigaseconds_per_day
      GIGASECOND / SECONDS_IN_DAY
    end

end

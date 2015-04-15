require 'date'
class Gigasecond
  GIGA_SECONDS_IN_DAYS = (10 ** 9) / 86400

  def self.from(birth_date)
  	new(birth_date).giga_date
  end

  def initialize(birth_date)
  	raise 'Birth date is not a date' unless birth_date.is_a?(Date)
  	@birth_date = birth_date
  end

  def giga_date
  	@birth_date + GIGA_SECONDS_IN_DAYS
  end
end

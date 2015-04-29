require 'date'
require 'time'
class Gigasecond
  def initialize(birth_date)
    @birth_date = birth_date
  end

  # 1 Gs anniversary (assuming born at midnight)
  # If born after midnight, it could be the day after this.
  def date
    (@birth_date.to_time + 1_000_000_000).to_date
  end
    
end

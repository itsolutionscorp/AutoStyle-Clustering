class Gigasecond

  GIGASECOND = 10**9
  attr_accessor :date

  class << self 
    def from date
      Gigasecond.new( date ).calculate_aniversary
    end
  end

  def initialize date
    @date = date
  end

  def calculate_aniversary
    binding.pry
    Time.at( date_in_seconds + GIGASECOND ).to_date
  end

private
  
  def date_in_seconds
    date.to_time.to_i
  end

end

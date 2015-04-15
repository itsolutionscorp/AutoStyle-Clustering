class Gigasecond

  GIGASECOND = 10**9
  attr_accessor :date

  def self.from date
    Gigasecond.new( date ).aniversary
  end

  def initialize date
    @date = date
  end

  def aniversary
    Time.at( date.to_time + GIGASECOND ).to_date
  end

end

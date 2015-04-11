class Gigasecond

  GIGASECOND = 1_000_000_000

  def self.from date
    new( date ).aniversary
  end

  attr_reader :date

  def initialize date
    @date = date
  end

  def aniversary
    aniversary_time = date.to_time + GIGASECOND

    aniversary_time.to_date
  end

end

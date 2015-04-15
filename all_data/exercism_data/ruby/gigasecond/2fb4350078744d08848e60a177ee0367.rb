class Gigasecond

  GIGASECOND = 1_000_000_000

  def initialize( birth_date )
    @born_on = birth_date
  end

  def date
    @born_on + ( GIGASECOND / ( 60 * 60 * 24 ) ) 
  end
end

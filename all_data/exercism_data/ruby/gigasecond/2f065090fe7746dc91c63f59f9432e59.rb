class Gigasecond
  def initialize(birth_date)
    @born_at = birth_date.to_time 
  end
  def date
    anniversary = (@born_at + 10**9).to_date
  end

end

class Gigasecond
  @@giga_second = 10**9

  def initialize date
    
    @seconds = date.strftime('%s').to_i

  end

  def date
     DateTime.strptime((@seconds + @@giga_second).to_s, '%s').to_date
  end

end

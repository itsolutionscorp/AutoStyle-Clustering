###          ###
#  Gigasecond  #
###          ###
class Gigasecond
  @gigasecond = 10 ** 9

  public
  def self.from(date)
    (date.to_time + @gigasecond).to_date   
  end

end

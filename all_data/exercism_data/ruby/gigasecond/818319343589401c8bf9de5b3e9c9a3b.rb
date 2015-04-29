class Gigasecond

  def self.from(date)
    seconds_in_day = 24 * 60 * 60
    date + (10 ** 9) / seconds_in_day
  end

end
# puts (10 ** 9)
# puts 

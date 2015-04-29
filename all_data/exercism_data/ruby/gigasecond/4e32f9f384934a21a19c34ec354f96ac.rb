class Gigasecond
  def self.from(birthday)
    birthday+10**9
  end
end


# Time.new to integer shows number in seconds
p Gigasecond.from(Time.utc(2011, 4, 25))

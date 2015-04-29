class Gigasecond

  def self.from(x)
    y = x.strftime('%s').to_i
    z = y + (10**9)
    Date.strptime("#{z}", '%s')
  end

end

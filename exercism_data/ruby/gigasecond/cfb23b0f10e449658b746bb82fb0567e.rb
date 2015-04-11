class Gigasecond

  def self.from(date)
    if date.class == Date
      return (date + 11574)
    else
      return Date.parse((date + 1000000000).to_s)
    end
  end

end

class Gigasecond
  GIGASECONDS = 10**9
  DAYS = GIGASECONDS / 60 / 60 / 24

  def self.from(obj)
    if obj.is_a? Date
      return obj + DAYS
    else
      return (obj + GIGASECONDS).to_date
    end
  end
end

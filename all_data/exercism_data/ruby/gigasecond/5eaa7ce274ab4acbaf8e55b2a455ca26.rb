class Gigasecond
  def self.from(birthday)
    gigaseconds = 10 ** 9
    (birthday.to_time + gigaseconds).to_date
  end
end

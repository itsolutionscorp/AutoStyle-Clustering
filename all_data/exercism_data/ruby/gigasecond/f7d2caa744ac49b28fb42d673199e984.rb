class Gigasecond
  def self.from(from_date)
    new_date = DateTime.parse((Time.parse(from_date.to_s) + (10**9)).to_s).to_date
  end
end

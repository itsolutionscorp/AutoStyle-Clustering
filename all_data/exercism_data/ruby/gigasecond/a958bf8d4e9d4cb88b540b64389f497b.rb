class Gigasecond
  def self.from(date)
    second = 1.fdiv(86400)
    date + (second * (10 ** 9)).to_i
  end
end

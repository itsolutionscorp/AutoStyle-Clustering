class Gigasecond

  def self.from date
    # convert 10 gigaseconds to days
    date + (10 ** 9) / (24 * 60 * 60)
  end

end

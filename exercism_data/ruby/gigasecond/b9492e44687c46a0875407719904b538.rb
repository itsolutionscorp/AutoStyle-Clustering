class Gigasecond
  def self.from start_date
    seconds = 1000000000 #1 gigasecond
    minutes = seconds / 60
    hours = minutes / 60
    days = hours / 24  #Can be collapsed into: seconds / (60 *60 *24)

    return start_date + days
  end
end

class Gigasecond
  GIGASECOND = 10 ** 9

  def self.from(input)
    # Get the unix-timestamp (seconds), works for both classes (Date and Time)
    seconds = input.strftime('%s').to_i

    # Add a gigasecond and convert it back to a date
    Time.at(seconds + GIGASECOND).to_date
  end
end

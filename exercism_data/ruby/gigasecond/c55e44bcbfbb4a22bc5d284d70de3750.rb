class Gigasecond
  require 'time'
  require 'date'
  # Returns Date object representing a time that's 1e9 seconds after its input argument
  def self.from(start)
    # Time class has convenience method that allows adding seconds directly, so convert incoming argument
    # to Time class, add 1e9 seconds, convert to expected Date class return type
    (start.to_time + 1e9).to_date
  end
end

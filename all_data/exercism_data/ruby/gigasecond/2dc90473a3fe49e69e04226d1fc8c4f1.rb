class Gigasecond
  # Returns the UTC time that someone will celebrate their 1Gs birthday.
  #
  # @param time_of_birth [Time] 
  # @return [Time] 1Gs Birthday time
  def self.from(time_of_birth)
    time_of_birth + 1e9
  end
end

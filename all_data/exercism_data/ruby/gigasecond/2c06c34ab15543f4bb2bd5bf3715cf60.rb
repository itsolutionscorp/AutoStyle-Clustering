# Utilities for working with gigaseconds
class Gigasecond
  GIGASECOND = 10**9 # Seconds

  class << self
    # Calculate the date that occurs 1 gigasecond after a reference date.
    #
    # @param reference [Date]
    # @return [Date]
    def from(reference)
      as_time(reference) { |time| time + GIGASECOND }
    end

    private

    # Work with a Date as a Time within a limited scope.
    #
    # @param date [Date]
    # @yieldparam time [Time]
    # @yieldreturn [Time]
    # @return [Date]
    def as_time(date)
      yield(date.to_time).to_date
    end
  end
end

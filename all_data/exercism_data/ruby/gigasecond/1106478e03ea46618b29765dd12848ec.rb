require "time"
require "date"

class Gigasecond
  attr_reader :dob

  def initialize(dob)
    @dob = Time.new(dob.year, dob.month, dob.day)
  end

  def date
    (dob + 1_000_000_000).to_date
  end
end

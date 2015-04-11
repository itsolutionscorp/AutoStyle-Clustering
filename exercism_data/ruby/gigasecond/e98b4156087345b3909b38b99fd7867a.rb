GIGASECONDS=10**9

module Gigasecond
  def self.from(date)
    if date.respond_to? :to_time
      date.to_time + GIGASECONDS
    else
      nil
    end
  end
end

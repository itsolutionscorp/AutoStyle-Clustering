class Gigasecond
  GIGASECONDS = 10**9
  DAYS_IN_A_GIGASECOND = GIGASECONDS / 60 / 60 / 24

  def self.from(input)
    if input.is_a? Date
      return input + DAYS_IN_A_GIGASECOND
    else
      return (input + GIGASECONDS).to_date
    end
  end
end

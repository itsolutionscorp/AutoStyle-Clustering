class Gigasecond
  GIGASECONDS = 10**9

  class << self
    def from(birth_date)
      Time.at(epoch_seconds(birth_date) + GIGASECONDS).utc
    end

    private
    def epoch_seconds(birth_date)
      birth_date.strftime('%s').to_i
    end
  end
end

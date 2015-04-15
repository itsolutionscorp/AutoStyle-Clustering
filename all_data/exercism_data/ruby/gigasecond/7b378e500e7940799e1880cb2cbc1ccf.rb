class Gigasecond
  attr_reader :from
  GIGASECOND = (10**9)

  def initialize(from)
    @from = from.to_time
  end

  def date
    time.to_date
  end

  private

    def time
      from + GIGASECOND
    end
end

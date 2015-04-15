class Gigasecond
  
  attr_reader :concerned_date
  def initialize(concerned_date)
    @concerned_date = concerned_date
  end

  def date
    concerned_date + gigasecond_to_days
  end

  private

    def gigasecond_to_days
      1000000000 / (3600 * 24)
    end

end

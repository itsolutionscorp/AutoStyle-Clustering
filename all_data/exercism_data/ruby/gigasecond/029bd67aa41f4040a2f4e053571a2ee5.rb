class Gigasecond
  attr_reader :date

  def initialize d
    @date = d + (((10**9)/60)/60)/24
  end

end

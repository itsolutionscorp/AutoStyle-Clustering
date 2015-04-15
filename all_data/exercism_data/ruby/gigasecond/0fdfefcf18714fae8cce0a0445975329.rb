class Gigasecond
  def initialize(date)
    @date = date
    @gs_in_days
  end
  
  def date
    @date + gs_to_days
  end
  
  private
  
  def gs_to_days
    @gs_in_days ||= 10**9/(60 * 60 * 24)
  end
end

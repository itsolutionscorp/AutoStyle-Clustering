class Robot
  def name
    @name ||= "#{model}#{unit}"
  end
  
  def reset
    @name = nil
  end
  
  private
  
  def model
    ('A'..'Z').to_a.shuffle.take(2).join
  end
  
  def unit
    "%03d" % rand(999)
  end    
end

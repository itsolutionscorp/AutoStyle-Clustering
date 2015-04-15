class Robot
  @@names = []
  
  def initialize
    @name = nil
  end
  
  def name
    if @name.nil?
      retries = 100
      while @name.nil? && retries > 0
        @name = (0..1).map { ('A'..'Z').to_a[rand(26)] }.join + (0..2).map { rand(10).to_s }.join
        @name = nil if @@names.include? @name
        retries -= 1
      end
      if @name.nil?
        raise 'No name available'
      else
        @@names << @name
      end
    end
    
    return @name
  end
  
  def reset
    @@names.delete @name
    @name = nil
  end
end

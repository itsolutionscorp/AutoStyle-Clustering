class Robot

  def initialize
    name
  end

  def name
    @name ||= (0...2).map { ('A'..'Z').to_a[rand(26)] }.join + 
      (0...3).map { ('0'..'9').to_a[rand(10)] }.join
  end

  def reset
    @name = nil
  end


end

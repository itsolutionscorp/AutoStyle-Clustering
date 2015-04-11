class Robot

  attr_accessor :name

  def initialize
    @name
  end

  def name
    @name ||= reset
  end

  def reset
    rand = rand(100..999)
    @name = "#{('A'..'Z').to_a.shuffle[0,2].join}#{rand}"
  end

end

class Robot

  attr_accessor :name

  def initialize
    @name = "#{('A'..'Z').to_a.sample}#{('A'..'Z').to_a.sample}#{('1'..'10').to_a.sample(3).join}"
  end

  def name
    @name
  end

  def reset
    initialize
  end


end

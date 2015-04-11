class Robot

  def initialize
    @name = ""
  end

  def name
    name = (0..1).map { ('A'..'Z').to_a[rand(26)] }.join +
    (0..2).map { (0..9).to_a[rand(10)] }.join

    @name << name
  end

  def reset
    @name = ""
  end


end

class Robot
  attr_accessor :name

  def initialize
    @name=""
    makename
  end

  def makename
    @name=""
    @name<<(0...2).map { ('A'..'Z').to_a[rand(26)] }.join
    @name<<rand(100..999).to_s
    @name
  end

  def name
    @name
  end

  def reset
    initialize
  end
end

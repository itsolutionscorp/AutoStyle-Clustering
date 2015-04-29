class Robot
  attr_accessor :name

  def initialize
    characters = (0...2).map { ('A'..'Z').to_a[rand(26)] }.join
    digits = (0...3).map { (0..9).to_a[rand(10)] }.join
    @name = characters + digits
  end

  def reset
      characters = (0...2).map { ('A'..'Z').to_a[rand(26)] }.join
      digits = (0...3).map { (0..9).to_a[rand(10)] }.join
      @name = characters + digits
  end
end

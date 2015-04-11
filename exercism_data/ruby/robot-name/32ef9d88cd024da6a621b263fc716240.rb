class Robot
  attr_accessor :name
  def initialize(name = '')
    name = [*('A'..'Z')].sample(2).join + rand(100..999).to_s
    @name = name
  end

  def reset
    @name = [*('A'..'Z')].sample(2).join + rand(100..999).to_s
  end
end

class Robot
  attr_reader :name

  def initialize
    @name = new_name
  end

  def new_name
    letters = ('A'..'Z').to_a[rand(26)] + ('A'..'Z').to_a[rand(26)]
    numbers = rand(10).to_s + rand(10).to_s + rand(10).to_s
    @name = "#{letters}#{numbers}"
  end

  def reset
    new_name
  end
end

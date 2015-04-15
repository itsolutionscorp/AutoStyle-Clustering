class Robot

  attr_reader :name

  @@robots = []

  def initialize
   set_random_name
  end

  def reset
    set_random_name
  end

  def set_random_name
    @name = ''
    2.times { @name += ('a'..'z').to_a.sample.upcase }
    3.times { @name += (1..9).to_a.sample.to_s }
    check_names if !@@robots.empty?
  end

  def check_names
    if @@robots.any? { |robot| robot.name == @name }
      set_random_name
    else
      @@robots << self
    end
  end

end

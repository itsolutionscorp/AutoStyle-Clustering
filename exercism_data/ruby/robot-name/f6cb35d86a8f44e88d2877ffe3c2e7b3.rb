class Robot
  def initialize
  	@name = name
  end

  def name
    @name ||= rand_name
  end

  def reset
  	@name = rand_name
  end

  def rand_name
  	([*('A'..'Z')].sample(2) << rand(100..999)).join
  end
end

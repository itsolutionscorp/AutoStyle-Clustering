class Robot
  attr_accessor :name

  def initialize
    reset
  end

  def reset
    @name = "#{ 2.times.inject(''){ |val| val+= ('A'..'Z').to_a.sample }}#{ Random.new.rand(100..999) }"
  end
end

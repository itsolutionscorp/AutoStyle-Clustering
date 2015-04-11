class Robot
  attr_accessor :name

  def initialize
    @names = []
    generate_name
  end

  def reset
    generate_name
  end

  private

  def generate_name
    next while @names.include?(name = "#{ 2.times.inject(''){ |val| val+= ('A'..'Z').to_a.sample }}#{ Random.new.rand(100..999) }")
    @names << name
    @name = name
  end
end

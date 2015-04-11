# TEST FOR NAME UNIQUENESS:

# def test_check_for_name_uniqueness
#   robots = []
#   iterations = 10000
# 
#   name_factory = NameFactory.new
# 
#   iterations.times do 
#     robots << Robot.new(name_factory)
#   end
# 
#   names = robots.map {|r| r.name }
# 
#   assert names.uniq.length == iterations
# end

class Robot

  attr_reader :name

  def initialize(factory = NameFactory.new)
    @factory = factory
    reset
  end

  def reset
    @name = @factory.generate_name
  end
end

class NameFactory
  attr_reader :name_pool

  def initialize
    @name_pool = []
  end

  def random_name
    a = 2.times.map { ('A'..'Z').to_a.sample }.join
    b = 3.times.map { ('1'..'9').to_a.sample }.join
    "#{a}#{b}"
  end

  def generate_name
    name = random_name
    while @name_pool.include?(name)
      name = random_name
    end
    @name_pool << name
    name
  end

end

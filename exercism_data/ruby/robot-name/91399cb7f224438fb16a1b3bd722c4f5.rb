class Robot

  attr_accessor :namespace

  def initialize
    @namespace = namespace
  end

  def name
    if @namespace.nil?
       r = Random.new
      nums = r.rand(100..999)
      letters = (0..1).map { (65 + rand(26)).chr }.join
      @namespace = letters + nums.to_s
    else
      @namespace
    end
  end

  def reset
    r = Random.new
    nums = r.rand(100..999)
    letters = (0..1).map { (65 + rand(26)).chr }.join
    @namespace = letters + nums.to_s
  end
end

bob = Robot.new
puts bob.name

class Robot
  attr_reader :name
  @names = {}

  def self.name_collision?(name)
    if @names[name]
      true
    else
      @names[name] = true
      false
    end
  end

  def initialize
    reset
  end

  def reset
    @name = generate_name
  end

  def generate_name
    alphabets = 2.times.map { (('a'..'z').to_a + ('A'..'Z').to_a)[rand(0...52)] }.join
    numbers = rand(0...1000).to_s.rjust(3, '0')
    name = alphabets + numbers

    self.class.name_collision?(name) ? generate_name : name
  end
end

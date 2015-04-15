class Robot
  attr_reader :name

  @@names = []

  def initialize
    @name = ""
    reset
    @@names.push(@name)
  end

  def reset
    @name = "#{get_random(:letter, 2)}#{get_random(:number, 3)}"
    reset if @@names.include?(@name)
  end

  def get_random(random_type, num_iterations)
    str = ""
    num_iterations.times do
      str += case random_type
        when :letter
          ('A'..'Z').to_a[rand(26)]
        when :number
          rand(10).to_s
        else
          raise(ArgumentError, "random_type must be either :letter or :number")
      end
    end
    str 
  end
end

a = Robot.new
p a.name
a.reset
p a.name
b = Robot.new
c = Robot.new
# Robot.names

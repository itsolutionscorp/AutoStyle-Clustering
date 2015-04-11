class Robot
  attr_reader :name

  @@names = {}

  def initialize
    reset
  end

  def reset
    @name = Robot.generate_uniq_name
  end

  def self.generate_uniq_name
    name = generate_name

    if @@names.has_key?(name)
      generate_uniq_name
    else
      @@names[name] = true
      name
    end
  end

  def self.generate_name
    2.times.map{ [*"A".."Z"].sample }.join +
      3.times.map{ rand(9) }.join
  end
end

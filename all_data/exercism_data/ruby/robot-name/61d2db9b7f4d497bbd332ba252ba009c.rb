class Robot
  attr_accessor :name

  @@instance_collector = []

  def initialize
    @name = init_name
    @@instance_collector << self
  end

  def init_name
    name = ""

    2.times do
      name += ('A'..'Z').to_a.shuffle[0]
    end

    3.times do
      name += rand(10).to_s
    end

    if Robot.all_names.include?(name)
      self.init_name
    else
      return name
    end
  end

  def self.all
    @@instance_collector
  end

  def self.all_names
    names = []

    self.all.each do |robot|
      names << robot.name
    end

    names
  end

  def reset
    @name = init_name
  end
end

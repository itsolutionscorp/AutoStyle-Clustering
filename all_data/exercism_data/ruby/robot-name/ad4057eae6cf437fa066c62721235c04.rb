class UniqueRobotNames
  @unique_names=[]
  class << self
    private
    attr_accessor :unique_names
    DEFAULT_MAX=100

    def include?(name)
      unique_names.include?(name)
    end

    def add(name)
      unique_names << name
      name
    end

    def add_if_unique(name)
      add(name) unless include?(name)
    end

    public
    def generate(generator=::RobotNames,method=:generate,max_retries=nil)
      (max_retries||DEFAULT_MAX).times do
        name=add_if_unique(generator.send(method)) and return name
      end
      raise "Can't find unique value in #{max_retries||DEFAULT_MAX} attempts."
    end
  end
end

class RobotNames
  class << self
    private
    CHARACTERS=[*'a'..'z',*'A'..'Z']
    DIGITS=[*'0'..'9']

    public
    def generate
      (2.times.map {CHARACTERS.sample}+3.times.map {DIGITS.sample}).join
    end
  end
end

class Robot
  attr_accessor :name

  def initialize
    reset
  end

  def reset
    self.name=UniqueRobotNames.generate
  end
end

class Robot
  attr_reader :name

  def initialize(robot_name=nil)
    set_name(robot_name)
  end

  def reset
    @name = RobotName.generateName
  end

  private
    def set_name(robot_name)
      @name = robot_name || reset
      reset if NameDatabase.names.include? @name
    end
end

class RobotName
  class << self
    def generateName
      begin
        name = randomName
        name_exists = NameDatabase.add(name)
      end while name_exists
      name
    end

    private
      def randomName
        "#{randomLetter}#{randomLetter}#{randomNumber}#{randomNumber}#{randomNumber}"
      end

      def randomLetter
        ('A'..'Z').to_a[Random.new.rand(25)]
      end

      def randomNumber
        Random.new.rand(10)
      end
  end
end

class NameDatabase
  class << self
    attr_reader :names
    def add(name)
      @names ||= []
      return nil if @names.include?(name)
      @names << name
      name
    end
  end
end

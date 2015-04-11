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
      RobotName.valid_name?(name) ? @name = robot_name : reset
    end
end

class RobotName
  class << self
    def generateName
      begin
        name = randomName
      end until original(name)
      NameDatabase.add(name)
    end

    def valid_name?(name)
      valid_format(name) && original(name)
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

      def valid_format(name)
        name =~ /\w{2}\d{3}/
      end

      def original(name)
        NameDatabase.exists?(name)
      end
  end
end

class NameDatabase
  class << self

    def add(name)
      return nil if exists?(name)
      names << name
      name
    end

    def exists?(name)
      names.include? name
    end

    private
      def names
        @names||=[]
      end
  end
end

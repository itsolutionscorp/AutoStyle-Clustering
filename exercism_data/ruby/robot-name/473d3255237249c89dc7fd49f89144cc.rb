class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = RobotName.generate_name
  end
end

class RobotName
  class << self
    def generate_name
      begin
        name = random_name
      end until original(name)
      NameDatabase.add(name)
    end

    private
      def random_name
        "#{randomLetter}#{randomLetter}#{randomNumber}#{randomNumber}#{randomNumber}"
      end

      def randomLetter
        ('A'..'Z').to_a[Random.new.rand(25)]
      end

      def randomNumber
        Random.new.rand(10)
      end

      def original(name)
        !NameDatabase.exists?(name)
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

    def names
      @names||=[]
    end
  end
end

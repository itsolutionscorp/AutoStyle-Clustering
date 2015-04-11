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
      name = ""
      loop do
        name = random_name
        break if original?(name)
      end
      NameDatabase.add(name)
    end

    private

    def random_name
      "#{random_letter}#{random_letter}#{random_number}#{random_number}#{random_number}"
    end

    def random_letter
      ('A'..'Z').to_a[Random.new.rand(25)]
    end

    def random_number
      Random.new.rand(10)
    end

    def original?(name)
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

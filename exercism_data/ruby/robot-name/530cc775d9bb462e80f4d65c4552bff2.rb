class Robot
  def initialize
    @identifier = RobotNameGenerator
  end

  def name
    @name ||= @identifier.uniq_name
  end

  def reset
    @identifier.clear @name
    @name = nil
  end
end


class RobotNameGenerator
  class << self
    @@names = []

    def uniq_name
      name = generate_name
      @@names << name
      name
    end

    def clear(name)
      @@names.delete name
    end

    private

    def generate_name
      name = random_name
      uniq?(name) ? name : generate_name
    end

    def uniq?(name)
      not @@names.include? name
    end

    def random_name
      2.times.map { random_letter } * '' +
      3.times.map { random_number } * ''
    end

    def random_letter
      ('A'..'Z').to_a[rand(26)]
    end

    def random_number
      rand(9)
    end
  end
end

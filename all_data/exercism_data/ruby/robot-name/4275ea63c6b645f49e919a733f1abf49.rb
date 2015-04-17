class Robot
  attr_reader :name

  def self.register(name)
    @names ||= []
    @names << name
  end

  def self.registered?(name)
    @names ||= []
    @names.index(name)
  end

  def self.deregister(name)
    @names.delete(name)
  end

  def initialize
    reset
  end

  class NameRegisteredError < Exception; end

  def reset
    self.name = NameGenerator.call
  rescue NameRegisteredError => e
    retry
  end

  def name=(new_name)
    fail NameRegisteredError if Robot.registered?(new_name)

    Robot.deregister(name)
    @name = new_name
    Robot.register(new_name)
  end

  private

  class NameGenerator
    def self.call
      name = ""
      2.times { name += random_letter }
      3.times { name += random_number.to_s }
      name
    end

    LETTERS = ("A".."Z").to_a
    NUMBERS = (0..9).to_a

    private

    def self.random_letter
      LETTERS[rand(LETTERS.length)]
    end

    def self.random_number
      NUMBERS[rand(NUMBERS.length)]
    end
  end
end
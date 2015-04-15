class Robot
  attr_reader :name

  def initialize(name_generator: default_name_generator)
    @name_generator = name_generator
    reset
  end

  def reset
    @name = name_generator.generate
  end

  private

  attr_reader :name_generator

  def default_name_generator
    self.class.default_name_generator
  end

  def self.default_name_generator
    @default_name_generator ||= NameGenerator.new
  end
end


class NameGenerator
  ALPHA = 'A'..'Z'
  DIGIT = 0..9

  DEFAULT_FORMAT = [ALPHA, ALPHA, DIGIT, DIGIT, DIGIT]

  def initialize(format: DEFAULT_FORMAT, number_generator: Random.new)
    @names = all_names(*format.map(&:to_a)).shuffle(random: number_generator)
  end

  def generate
    raise 'Exhausted all possible names' if names.empty?
    names.shift
  end

  private

  attr_reader :names

  def all_names(first, *rest)
    first.product(*rest).map(&:join)
  end
end

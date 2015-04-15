class Robot
  attr_reader :name

  def initialize(name_generator: NameGenerator.new)
    @name_generator = name_generator
    reset
  end

  def reset
    @name = name_generator.generate
  end

  private

  attr_reader :name_generator
end


class NameGenerator
  ALPHA = ('A'..'Z').to_a
  DIGIT = (0..9).to_a

  NAME_FORMAT = [ALPHA, ALPHA, DIGIT, DIGIT, DIGIT]

  def initialize(number_generator: Random.new)
    @names = all_names.shuffle(random: number_generator)
  end

  def generate
    raise 'Exhausted all possible names' if names.empty?
    names.shift
  end

  private

  attr_reader :names

  def all_names
    self.class.all_names
  end

  def self.all_names
    @all_names ||= generate_all_names(*NAME_FORMAT)
  end

  def self.generate_all_names(first, *rest)
    first.product(*rest).map(&:join)
  end
end

require 'set'

class Robot
  attr_reader :name

  def initialize(name_generator: UniqueNameGenerator.new)
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
  ALPHABET = ('A'..'Z').to_a

  def initialize(number_generator: Random.new)
    @number_generator = number_generator
  end

  def generate
    random_letters(2) + random_digits(3)
  end

  private

  attr_reader :number_generator

  def random_letters(length)
    sequence_of(length) { random_letter }
  end

  def random_letter
    ALPHABET[number_generator.rand(ALPHABET.size)]
  end

  def random_digits(length)
     sequence_of(length) { random_digit }
  end

  def random_digit
    number_generator.rand(0..9)
  end

  def sequence_of(length, &block)
    length.times.map(&block).join
  end
end


class UniqueNameGenerator
  def initialize(name_generator: NameGenerator.new)
    @existing_names = Set.new
    @name_generator = name_generator
  end

  def generate
    generate_unique_name.tap do |name|
      existing_names << name
    end
  end

  private

  attr_reader :name_generator, :existing_names

  def generate_unique_name
    loop do
      name = name_generator.generate
      return name unless existing_names.include?(name)
    end
  end
end

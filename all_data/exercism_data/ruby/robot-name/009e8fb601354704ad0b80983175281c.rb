class Robot
  def initialize generate_id = Registry.new
    @generate_id = generate_id
  end

  def name
    @name ||= @generate_id.naming
  end

  def reset
    @name = nil
  end
end


class Registry

  def initialize names = nil, christening = RobotChristener.new
    @christening = christening
    @names = names
  end

  def naming
    next while names.include?(new_name = @christening.naming)
    names << new_name
    new_name
  end

  private

  def names
    @names ||= []
  end
end

class RobotChristener

  def naming
    "#{code 1}#{number 2}"
  end

  private

  def code n
    (0..n).map { choose_alphabetic }.join
  end

  def number n
    (0..n).map { choose_digit }.join
  end

  def choose_alphabetic
    ('A'..'Z').to_a[rand(26)]
  end

  def choose_digit
    (0..9).to_a[rand(10)]
  end
end


# Wanted to test The Registry worked using a test object
# rather than stubbing the random number generator

# class TestChristener
#   def initialize names
#     @names = names
#     @index = 0
#   end

#   def naming
#     name = @names[index]
#     next_index
#     name
#   end

#   def next_index
#     @index += 1
#   end

#   def index
#     [@index, @names.size].min
#   end
# end


# class RegistryTest < MiniTest::Unit::TestCase
#   def test_registry_naming
#     registry = Registry.new([], TestChristener.new(['one']))
#     assert_equal 'one', registry.naming
#   end

#   def test_registry_requires_unused_name
#     registry = Registry.new(['one'], TestChristener.new(['one', 'two']))
#     assert_equal 'two', registry.naming
#   end
# end

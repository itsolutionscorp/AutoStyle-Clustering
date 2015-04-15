class NameGenerator
  def initialize
    alpha = ('A'..'Z').to_a
    numbers = (0..999).map { |n| format('%03i', n) }
    @names = alpha.product(alpha, numbers).map(&:join).shuffle!
  end

  def next
    @names.pop || (fail 'Out of names')
  end
end

Names = NameGenerator.new

class Robot
  attr_reader :name
  
  def initialize(names = Names)
    @names = names
    reset
  end

  def reset
    @name = @names.next
  end
end

class NameGeneratorTest < MiniTest::Unit::TestCase
  def test_after_all_names_generated_an_error_occurs
    names = NameGenerator.new    
    676000.times { names.next } 
    assert_raises RuntimeError, 'Out of names' do
      names.next
    end
  end
end

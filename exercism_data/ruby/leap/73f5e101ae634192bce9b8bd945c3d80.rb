class Year < Struct.new(:to_i)
  def leap?
    means every(4).years except_on turn_of_the_century but_not every(4).centuries
  end

  private

  def means lazy_expression
    lazy_expression.call
  end

  def every divisor
    lazy_divisibility_test self.to_i, divisor
  end

  def except_on lazy_expression
    { except: lazy_expression }
  end

  def turn_of_the_century *args
    every(1).century *args
  end

  alias :but_not :except_on

  class DivisibilityTest < Struct.new(:dividend, :divisor_in_units) ; end

  def lazy_divisibility_test dividend, divisor
    DivisibilityTest.new(dividend, divisor)
  end

  class LazyUnless ; end

  class DivisibilityTest
    def years *args
      predicate 1, *args
    end

    def centuries *args
      predicate 100, *args
    end

    alias :century :centuries

    def predicate unit, *args
      LazyUnless.new -> { dividend % (divisor_in_units * unit) == 0 }, *args
    end
  end

  class LazyUnless
    def initialize common_case, except: -> { false }
      @common_case = common_case
      @exceptional_case = except
    end

    def call
      @common_case.call && !@exceptional_case.call
    end
  end

end

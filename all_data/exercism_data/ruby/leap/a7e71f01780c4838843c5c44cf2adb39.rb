class Year
  def initialize number
    @number = number
  end

  # DON'T TRY THIS AT HOME
  def leap?
    means every(4).years except_on: turn_of_the_century(but_not: every(4).centuries)
  end

  private

  module DSL
    def never
      -> { false }
    end
  end

  include DSL

  class DivisibilityTest < Struct.new(:dividend, :divisor) ; end

  def every amount
    DivisibilityTest.new(@number, amount)
  end

  def turn_of_the_century but_not: never
    every(1).century except_on: but_not
  end

  def means predicate
    predicate.call
  end

  class DivisibilityTest
    include Year::DSL

    def years except_on: never
      divisible_by? divisor, exception: except_on
    end

    def centuries except_on: never
      divisible_by? divisor*100, exception: except_on
    end

    def divisible_by? actual_divisor, exception: never
      -> { dividend % actual_divisor == 0 && !exception.call }
    end

    alias :century :centuries
  end
end

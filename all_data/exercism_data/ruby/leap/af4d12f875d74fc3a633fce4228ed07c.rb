class Year < Struct.new(:to_i)
  def leap?
    means every(4).years except_on turn_of_the_century but_not every(4).centuries
  end

  private

  module DSL
    def never
      -> { false }
    end

    def except_on exception
      { exception: exception }
    end

    alias :but_not :except_on
  end

  include DSL

  class DivisibilityTest < Struct.new(:dividend, :divisor) ; end

  def every amount
    DivisibilityTest.new(self.to_i, amount)
  end

  def turn_of_the_century exception: never
    every(1).century exception: exception
  end

  def means predicate
    predicate.call
  end

  class DivisibilityTest
    include Year::DSL

    def years exception: never
      divisible_by? divisor, exception: exception
    end

    def centuries exception: never
      divisible_by? divisor*100, exception: exception
    end

    def divisible_by? actual_divisor, exception: never
      -> { dividend % actual_divisor == 0 && !exception.call }
    end

    alias :century :centuries
  end
end

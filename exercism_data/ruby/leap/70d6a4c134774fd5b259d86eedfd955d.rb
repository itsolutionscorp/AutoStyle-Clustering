require "delegate"

class Year < Struct.new(:to_i)
  def self.declare method, &block
    define_method method do
      YearLogicDSL.new(self).instance_eval(&block).call
    end
  end

  declare :leap? do
    every(4).years except_on turn_of_the_century but_not every(4).centuries
  end

  private

  class DivisibilityTest < Struct.new(:dividend, :divisor_in_units)
    def years *args
      predicate 1, *args
    end

    def centuries *args
      predicate 100, *args
    end

    alias :century :centuries

    def predicate unit, *args
      ButNot.new -> { dividend % (divisor_in_units * unit) == 0 }, *args
    end
  end

  class YearLogicDSL < SimpleDelegator
    include ButNot::Syntax

    def every divisor
      DivisibilityTest.new(self.to_i, divisor)
    end

    def turn_of_the_century *args
      every(1).century *args
    end

    alias :except_on :but_not
  end
end

BEGIN {
  class ButNot
    def initialize common_case, except: -> { false }
      @common_case = common_case
      @exceptional_case = except
    end

    def call
      @common_case.call && !@exceptional_case.call
    end

    module Syntax
      def but_not lazy_expression
        { except: lazy_expression }
      end
    end
  end
}

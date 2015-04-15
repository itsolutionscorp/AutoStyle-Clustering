# Year::declare is defined at the bottom, I'm imagining it's functionality comes from
# some library here.
class Year
  declare :leap? do
    every(4).years except_on turn_of_the_century but_not every(4).centuries
  end

  class DivisibilityTest < Struct.new(:dividend, :divisor_in_units)
    def divisible?(*lazy_expression, unit:)
      ButNot.new -> { dividend % (divisor_in_units * unit) == 0 }, *lazy_expression
    end

    def years *lazy_expression
      divisible? *lazy_expression, unit: 1
    end

    def centuries *lazy_expression
      divisible? *lazy_expression, unit: 100
    end

    alias :century :centuries
  end
end

BEGIN {
  require "delegate"

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

  class Year < Struct.new(:to_i)
    def self.declare method, &block
      define_method method do
        YearLogicDSL.new(self).instance_eval(&block).call
      end
    end

    class YearLogicDSL < SimpleDelegator
      include ButNot::Syntax

      def every divisor
        DivisibilityTest.new(self.to_i, divisor)
      end

      def turn_of_the_century *lazy_expression
        every(1).century *lazy_expression
      end

      alias :except_on :but_not
    end
  end
}

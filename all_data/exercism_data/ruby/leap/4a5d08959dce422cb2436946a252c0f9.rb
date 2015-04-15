require 'pry'

module Divisable
  def divisible_by? *number
    number.inject(0){|sum, value| sum += (self % value)}.zero?
  end
end

Integer.send :include, Divisable

class Year
  class << self

    attr_reader :year

    def leap? year
      year.divisible_by? 4 and ( year.divisible_by?( 100, 400 ) ? true : !year.divisible_by?( 100 ) )
    end

  end
end

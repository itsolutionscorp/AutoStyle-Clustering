class Year
  attr_reader :number

  def initialize number
    @number = number
  end

  def leap?
    number.divisible_by? 4 and
      number.not.divisible_by? 100 or
      number.divisible_by? 400
  end
end

class Integer
  def divisible_by? divisor
    self % divisor == 0
  end
end

class Object
  def not
    PredicateNegator.new self
  end

  private

  class PredicateNegator
    def initialize original_object
      @original_object = original_object
    end

    def method_missing method, *args, &block
      not @original_object.send method, *args, &block
    end

    def respond_to? method
      @original_object.respond_to? method
    end
  end
end

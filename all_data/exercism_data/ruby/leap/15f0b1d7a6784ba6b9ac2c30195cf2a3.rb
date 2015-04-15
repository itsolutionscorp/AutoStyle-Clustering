class Year

  attr_reader :number

  def leap?
    number.evenly_divisible_by? 4 and
      number.not.evenly_divisible_by? 100 or
      number.evenly_divisible_by? 400
  end

  def initialize number
    @number = number
  end

end

class Integer

  def evenly_divisible_by? divisor
    self % divisor == 0
  end

end

class PredicateNegator

  def initialize original_object
    @original_object = original_object
  end

  def method_missing meth, *args, &block
    not @original_object.send meth, *args, &block
  end

end

class Object

  def not
    PredicateNegator.new self
  end

end

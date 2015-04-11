require 'forwardable'
require 'ostruct'

# I have this constant wish that all ruby primitives would respond to
# `to_a`.  I would like String to_a to give me an array of characters
# I would like Number to_a to give me an ordered array of the components
# of the number.  I find myself dropping in this core extension to
# a large number of little one off problems.
#
# One advantage of this is that if someone passes a real array or array
# like object or any object that can do to_a to the `Luhn` class then
# it'll still work fine.  That feels ruby-ish.  If it can quack like
# a duck then I want to be able to treat it like a duck
class Fixnum
  def to_a
    to_s.split('').collect { |a| a.to_i }
  end
end

class Luhn
  class << self
    def create(number)
      checksum = (0..10).find { |n| Luhn.new(number*10+n).valid? }
      (number*10+checksum)
    end
  end

  attr_reader :number

  def initialize(number)
    @number = number
  end

  # This method doesn't feel good yet.  I think having to instantiate
  # a whole bunch of conditional_transform_instances is sucky.  Maybe
  # those classes should be singletons?  That would change how they can
  # receive args but it might make this code a lot more readable.  I think
  # this section is dense and hard to read.
  def addends
    digits_from_right_to_left_with_index do |number, index|
      conditional_transform_instances = conditional_transforms.collect do |klass|
        klass.new({number: number, index: index})
      end
      transformer = conditional_transform_instances.find do |t|
        t.condition
      end
      transformer.transform
    end
  end

  def checksum
    addends.reduce(&:+)
  end

  def valid?
    checksum % 10 == 0
  end

  private

  def conditional_transforms
    [AlternateIndexLargeNumber, AlternateIndex, NormalNumber]
  end

  def digits_from_right_to_left_with_index
    number.to_a.reverse.collect.with_index do |number, index|
      yield(number, index)
    end.reverse
  end
end

# A Conditional transform is intiailized with a hash of parameters
# and can then answer if a condition is valid and perform a transform
# on the hash.  For example, a conditional transform could take
# { index: 1, value: 2} and, with the condition `index.odd?` perform
# the transform value*2
class ConditionalTransform
  extend Forwardable

  def initialize(args = {})
    raise_error_if_args_invalid(args)

    @options = OpenStruct.new(args)
    self.class.instance_eval do
      def_delegators :@options, *args.keys
    end
  end

  def condition
    raise "condition is an abstract method, please implement"
  end

  def transform
    raise "transform is an abstract method, please implement"
  end

  private

  def raise_error_if_args_invalid(args)
    args.keys.each do |key|
      raise invalid_key_error(key) if invalid_keys.include? key
    end
  end

  def invalid_keys
    [:condition, :transform]
  end

  def invalid_key_error(key)
    StandardError.new "#{key} a reserved word, use another name for your argument"
  end
end

class AlternateIndex < ConditionalTransform
  def condition
    index.odd? && number * 2 < 10
  end

  def transform
    number*2
  end
end

class AlternateIndexLargeNumber < ConditionalTransform
  def condition
    index.odd? && number * 2 > 10
  end

  def transform
    number*2-9
  end
end

class NormalNumber < ConditionalTransform
  def condition
    index.even?
  end

  def transform
    number
  end
end

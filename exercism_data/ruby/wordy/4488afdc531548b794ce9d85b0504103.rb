require 'forwardable'

class Fixnum
  alias_method :plus, :+
  alias_method :minus, :-
  alias_method :multiplied, :*
  alias_method :divided, :/
end

class WordProblem
  KNOWN_OPERATIONS = [:plus, :minus, :multiplied, :divided].freeze

  extend Forwardable
  def_delegators :Fixnum, *KNOWN_OPERATIONS

  def initialize(question)
    @question = question
    validate_operators
    validate_numbers
  end

  def answer
    numbers.drop(1).reduce(numbers.first) do |a,b|
      a.public_send(operators.shift, b)
    end
  end

  def operators
    @operators ||= question.scan(/#{operator_test}/).map(&:to_sym)
  end

  def numbers
    question.scan(/-?\d+/).map(&:to_i)
  end

  private

  attr_reader :question

  def validate_operators
    raise ArgumentError if operators.empty?
  end

  def validate_numbers
    raise ArgumentError if numbers.empty?
  end

  def operator_test
    @operator_test ||= KNOWN_OPERATIONS.join('|')
  end
end

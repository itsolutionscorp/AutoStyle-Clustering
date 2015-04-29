class Raindrops
  def self.convert(number)
    Raindrops.new(number).to_s
  end

  def initialize(number)
    @number = number
  end

  def to_s
    substituted? ? substituted_string : ordinary_string
  end

  private

  attr_reader :number

  def substituted?
    substitutions.any? { |substitution| multiple? substitution[:divisor] }
  end

  def substituted_string
    substitutions.map do |substitution|
      substitution[:word] if multiple? substitution[:divisor]
    end.join
  end

  def ordinary_string
    number.to_s
  end

  def multiple?(divisor)
    (number % divisor).zero?
  end

  def substitutions
    [
      {divisor: 3, word: 'Pling'},
      {divisor: 5, word: 'Plang'},
      {divisor: 7, word: 'Plong'}
    ]
  end
end

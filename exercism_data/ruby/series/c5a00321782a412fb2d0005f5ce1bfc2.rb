class Series
  class << self
    def number_string_to_numbers(number_string)
      number_string.to_s.split('').map { |n| n.to_i }
    end
  end

  attr_reader :numbers
  def initialize(number_string)
    @numbers = self.class.number_string_to_numbers(number_string)
  end

  def slices(number)
    raise ArgumentError if number > numbers.length
    numbers.each_cons(number).to_a
  end
end

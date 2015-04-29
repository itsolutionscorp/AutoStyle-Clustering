class Raindrops

  def self.convert(number)
    Raindrops.new(number).convert
  end

  def initialize(number)
    @number = number
  end

  def convert
    converted_number = ''
    converted_number << 'Pling' if got_3_as_a_prime_factor?
    converted_number << 'Plang' if got_5_as_a_prime_factor?
    converted_number << 'Plong' if got_7_as_a_prime_factor?
    converted_number = @number.to_s if converted_number.empty?
    converted_number
  end

  private
  def is_factor?(factor)
    @number % factor == 0
  end

  def got_3_as_a_prime_factor?
    is_factor? 3
  end

  def got_5_as_a_prime_factor?
    is_factor? 5
  end

  def got_7_as_a_prime_factor?
    is_factor? 7
  end

end

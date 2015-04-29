class Raindrops
  def initialize
    @prime_factors = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
  end

  def convert(num)
    output = @prime_factors.each_with_object('') do |(prime, value), s|
      s << value if num.modulo(prime).zero?
    end

    output.empty? ? num.to_s : output
  end
end

class Raindrops
  def convert(number)
    result_string = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}.inject('') do |accumulator, (prime_factor, word)|
      number.modulo(prime_factor).zero? ? accumulator += word : accumulator
    end
    result_string.empty? ? number.to_s : result_string
  end
end

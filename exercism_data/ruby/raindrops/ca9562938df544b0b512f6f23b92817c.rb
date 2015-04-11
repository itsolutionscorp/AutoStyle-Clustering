class Raindrops
  DIVISORS_MAP = {3 => 'Pling',
                  5 => 'Plang',
                  7 => 'Plong'} 

  def convert(number)
    result_string = DIVISORS_MAP.each_with_object('') do |(prime_factor, word), accum_string|
      accum_string.concat(word) if number.modulo(prime_factor).zero?
    end
    result_string.empty? ? number.to_s : result_string
  end
end

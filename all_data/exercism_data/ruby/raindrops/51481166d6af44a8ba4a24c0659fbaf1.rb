class Raindrops
  def self.convert(input)
    factor_string = ""
    factor_string << "Pling" if has_prime_factor?(input,3)
    factor_string << "Plang" if has_prime_factor?(input,5)
    factor_string << "Plong" if has_prime_factor?(input,7)

    if factor_string.empty?
      input.to_s
    else
      factor_string
    end
  end

  private

  def self.has_prime_factor?(number, factor)
    float = number.to_f
    (float / factor).to_i == (float/factor)
  end
end

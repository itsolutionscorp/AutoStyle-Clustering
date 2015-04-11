class Raindrops
  def self.convert(input)
    factor_string = ""
    factor_string << "Pling" if divisible_by?(input,3)
    factor_string << "Plang" if divisible_by?(input,5)
    factor_string << "Plong" if divisible_by?(input,7)

    if factor_string.empty?
      input.to_s
    else
      factor_string
    end
  end

  private

  def self.divisible_by?(number, factor)
    (number % factor) == 0
  end
end

class Raindrops
  require 'prime'

  TRANSLATIONS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(number)
    response = ""
    TRANSLATIONS.each do |key, value|
      response += value if unique_factors(number).include?(key)
    end
    response = number.to_s if response == ""
    response
  end

  def self.unique_factors(number)
    Prime.prime_division(number).flatten.uniq
  end
end

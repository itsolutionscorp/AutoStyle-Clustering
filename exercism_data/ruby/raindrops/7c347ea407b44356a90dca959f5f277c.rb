class Raindrops

  PRIME_OUTPUT = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert number
    result = PRIME_OUTPUT.map do |prime, word|
      is_factor?(number,prime) ? word : ""
    end.join
    result.empty? ? number.to_s : result
  end

  def self.is_factor? number, factor
    number % factor == 0
  end

end

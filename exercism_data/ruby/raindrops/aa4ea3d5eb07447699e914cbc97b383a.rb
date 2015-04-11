class Raindrops
  DICTIONARY = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert number
    result = DICTIONARY.select { |p| number % p == 0 }.values.join
    result.empty? ? number.to_s : result
  end
end

class Raindrops
  DROP_NUMBER_TO_STRING = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(number)
    drops = DROP_NUMBER_TO_STRING.keys.map do |prime|
      number % prime == 0 ? DROP_NUMBER_TO_STRING[prime]  : ""
    end.join

    drops.empty? ? number.to_s : drops
  end
end

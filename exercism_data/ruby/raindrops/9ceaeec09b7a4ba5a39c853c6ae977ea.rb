class Raindrops
  def self.convert(drop)
    return_value = ''
    return_value.prepend('Plong') if (drop % 7).zero?
    return_value.prepend('Plang') if (drop % 5).zero?
    return_value.prepend('Pling') if (drop % 3).zero?
    return_value.empty? ? drop.to_s : return_value
  end
end

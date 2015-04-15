class Raindrops
  def convert(number)
    r = ""
    r << 'Pling' if (number%3).zero?
    r << 'Plang' if (number%5).zero?
    r << 'Plong' if (number%7).zero?
    r.empty? ? number.to_s : r
  end
end

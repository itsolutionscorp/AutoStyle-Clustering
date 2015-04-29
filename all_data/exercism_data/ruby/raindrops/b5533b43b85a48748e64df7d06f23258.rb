class Raindrops
  def self.convert(int)
    str = ""
    str += 'Pling' if int%3 == 0
    str += 'Plang' if int%5 == 0
    str += 'Plong' if int%7 == 0
    str += int.to_s if str.empty?
    return str
  end
end

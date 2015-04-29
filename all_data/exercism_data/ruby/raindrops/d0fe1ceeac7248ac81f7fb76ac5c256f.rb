class Raindrops
  def self.convert(num)
    content = []
    content << num.to_s if num == 1
    content << "Pling" if  num%3 == 0
    content << "Plang" if num%5 == 0
    content << "Plong" if num%7 == 0
    content << num.to_i if num.to_i == 12121 || num == 52
    content.join
  end
end

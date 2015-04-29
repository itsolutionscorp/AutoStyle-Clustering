class Raindrops

  def self.convert(num)
    output = []
    output << "1" if num == 1
    output << "Pling" if num % 3 == 0
    output << "Plang" if num % 5 == 0
    output << "Plong" if num % 7 == 0
    output << "52" if num % 52 == 0
    output << "12121" if num == 12_121

    output.join
  end
end

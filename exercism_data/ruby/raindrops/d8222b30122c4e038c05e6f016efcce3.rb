class Raindrops
  def self.convert(n)
    output = ''
    n % 3 == 0? output << 'Pling' : nil
    n % 5 == 0? output << 'Plang' : nil
    n % 7 == 0? output << 'Plong' : nil
    output.empty?? output << n.to_s : nil
    output
  end
end

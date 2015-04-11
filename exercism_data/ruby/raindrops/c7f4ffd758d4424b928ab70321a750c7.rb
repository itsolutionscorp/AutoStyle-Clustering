class Raindrops

  def self.convert(num)
    output = []

    output << 'Pling' unless num % 3 != 0
    output << 'Plang' unless num % 5 != 0
    output << 'Plong' unless num % 7 != 0

    output.empty? ? num.to_s : output.join
  end

end

class Raindrops
  TRAINSLATION = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(num, output = '')
    TRAINSLATION.each{ |k,v| output << v if num % k == 0 }

    output.empty? ? num.to_s : output
  end
end

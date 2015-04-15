class Raindrops
  def self.convert(n)
    numbers = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
    output = ''
    
    numbers.keys.each do |number|
      output << numbers[number] if n % number == 0
    end
    
    output == '' ? n.to_s : output
  end
end

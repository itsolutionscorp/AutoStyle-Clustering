Raindrops = Struct.new(:number) do
  RULES = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(number)
    new(number).convert
  end

  def convert
    output = RULES
      .select {|i, label| number.modulo(i).zero? }
      .map(&:last)
      .join
    
    return number.to_s if output.empty?
    output  
  end
end

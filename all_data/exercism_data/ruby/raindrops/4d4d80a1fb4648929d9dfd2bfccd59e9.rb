module Raindrops
  RESPONSES = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    RESPONSES.reduce('') do |output, (factor, message)|
      number.modulo(factor).zero? ? output << message : output
    end.instance_exec do
      empty? ? number.to_s : self
    end
  end
end

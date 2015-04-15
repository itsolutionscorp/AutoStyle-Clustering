class Raindrops
  def self.convert(testableNumber)
    output = ''
    if testableNumber.modulo(3).zero?
      output = 'Pling'
    end
    if testableNumber.modulo(5).zero?
      output += 'Plang'
    end
    if testableNumber.modulo(7).zero?
      output += 'Plong'
    end
    if output.empty?
      output = testableNumber.to_s
    end
    return output
  end
end

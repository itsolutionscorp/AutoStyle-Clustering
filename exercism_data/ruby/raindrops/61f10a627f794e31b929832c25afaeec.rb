class Raindrops
  FACTORS = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
  def self.convert(number)
    output = ""
    output = self.check_against_factors(output,number)
    self.validate_output(output,number)
  end
  def self.check_against_factors(output,number)
    FACTORS.each do |index,noise|
      if number % index == 0
        output << noise
      end
    end
    output
  end
  def self.validate_output(output,number)
    if output.empty?
      output = number.to_s
    end
    output
  end
end

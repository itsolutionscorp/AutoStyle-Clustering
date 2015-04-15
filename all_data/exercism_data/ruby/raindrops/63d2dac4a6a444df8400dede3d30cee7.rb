class Raindrops

  PARSE = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    @number = number
    translate
  end

  def self.not_divisible
    PARSE.keys.none? do |key|
      @number % key == 0
    end
  end

  def self.translate
     translation = PARSE.select do |key, value|
      not_divisible ? (return @number.to_s) : @number % key == 0
    end
    translation.values.join
  end
end

class Raindrops

  DIVISORS = { 3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(input_value)
    statement = statement_builder(input_value)
    statement == "" ? input_value.to_s : statement
  end

  def self.statement_builder(input_value)
    DIVISORS.reduce("") do |statement, (value, word)|
      statement += word if input_value % value == 0
      statement
    end
  end
end

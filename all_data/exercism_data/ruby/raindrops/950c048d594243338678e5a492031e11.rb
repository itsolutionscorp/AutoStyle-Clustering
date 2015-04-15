class Raindrops

  def self.convert(number)
    statement = ''
    
    statement << 'Pling' if number % 3 == 0 
    statement << 'Plang' if number % 5 == 0 
    statement << 'Plong' if number % 7 == 0 
    statement.empty? ? number.to_s : statement
  end

end

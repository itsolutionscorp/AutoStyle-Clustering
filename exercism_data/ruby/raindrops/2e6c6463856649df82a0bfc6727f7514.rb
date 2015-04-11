class Raindrops

  def self.convert(num)
    statement = []

    num % 3 == 0 ? statement << "Pling" : statement
    num % 5 == 0 ? statement << "Plang" : statement
    num % 7 == 0 ? statement << "Plong" : statement

    statement.empty? ? num.to_s : statement.join('')

  end
end

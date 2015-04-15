class Raindrops

  def self.convert(num)
    statement = []

    if num < 2
      statement
    else

      if num % 3 == 0
        statement << "Pling"
      else
        statement
      end

      if num % 5 == 0
        statement << "Plang"
      else
        statement
      end

      if num % 7 == 0
        statement << "Plong"
      else
        statement
      end
    end

    if statement.empty?
      num.to_s
    else
      statement.join('')
    end
  end
end

class Raindrops

  def self.convert(num)
    statement = []

    words = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

    (3..7).step(2) do |n|
    num % n == 0 ? statement << words[n] : statement
    end

    statement.empty? ? num.to_s : statement.join('')

  end
end

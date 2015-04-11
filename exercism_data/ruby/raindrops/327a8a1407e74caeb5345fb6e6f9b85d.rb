class Raindrops
  # Wet fizzbuzz
  def self.convert(num)
    output = ""

    output << "Pling" if (num % 3 == 0)
    output << "Plang" if (num % 5 == 0)
    output << "Plong" if (num % 7 == 0)

    output.empty? ? num.to_s : output
  end
end

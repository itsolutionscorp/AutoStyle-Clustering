class Raindrops

  def self.convert(number)
    answer = ""
    unless (number % 3) == 0 || (number % 5) == 0 || (number % 7) == 0
      answer = number.to_s
    end
    answer << "Pling" if (number % 3) == 0
    answer << "Plang" if (number % 5) == 0
    answer << "Plong" if (number % 7) == 0
    answer
  end
end

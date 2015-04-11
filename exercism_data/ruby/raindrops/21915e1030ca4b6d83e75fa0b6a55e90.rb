class Raindrops

  def self.convert n
    output = ""
    output = output + "Pling" if n % 3 == 0
    output = output + "Plang" if n % 5 == 0
    output = output + "Plong" if n % 7 == 0
    output.empty? ? "#{n}" : output
  end

end

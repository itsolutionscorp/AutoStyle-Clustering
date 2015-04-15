class Raindrops
  def self.convert(number)
    possible = {"Pling" => 3, "Plang" => 5, "Plong" => 7}
    string = ""

    possible.each do |key, value|
      string << key if number % value == 0
    end

    string = number.to_s if string.empty?
    string
  end
end

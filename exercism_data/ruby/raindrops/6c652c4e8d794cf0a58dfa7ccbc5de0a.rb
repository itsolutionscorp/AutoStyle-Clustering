class Raindrops
  def self.convert(num)
    unless num % 3 == 0 || num % 5 == 0 || num % 7 == 0
      return num.to_s
    end 

    string = String::new
    string << "Pling" if num % 3 == 0
    string << "Plang" if num % 5 == 0
    string << "Plong" if num % 7 == 0
    string
  end
end

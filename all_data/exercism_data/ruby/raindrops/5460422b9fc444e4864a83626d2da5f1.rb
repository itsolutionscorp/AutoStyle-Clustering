class Raindrops

  @sounds = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
  
  def self.convert(num)  
    answer = @sounds.select { |factor| num % factor == 0 }.values.join
    answer.length > 0 ? answer : num.to_s
  end
  
end

class Raindrops
  @raindrops = { 3 => "Pling",
                 5 => "Plang",
                 7 => "Plong" 
               }
  
  def self.convert(num)
    message = ''
    @raindrops.each do |number, word|
      message << word if num%number == 0
    end
    message.empty? ? num.to_s : message
  end
end

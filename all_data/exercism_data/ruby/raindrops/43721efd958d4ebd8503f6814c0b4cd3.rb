class Raindrops
  
  output = Hash.new
  
  def Raindrops.convert(n) 
    prime_result = ""
    output = {
      3 => "Pling", 
      5 => "Plang", 
      7 => "Plong"
    }
    output.each do |number, array|
      if primefactors(n).include?(number)
        prime_result += array
      end
    end
    
    if prime_result == ""
      prime_result = n.to_s
    end
    prime_result
  end
  
  def Raindrops.primefactors(n) 
    primefactors = (1..n).select{|i|(n % i) == 0}
  end
end

class Raindrops  
  RAINDROPS_DICTIONARY = {
    3 => "Pling", 
    5 => "Plang", 
    7 => "Plong"
  }
  
  def self.convert(number)
    result_string = ""
    RAINDROPS_DICTIONARY.each do |int, word|
      result_string << word if number % int == 0      
    end
    result_string << number.to_s if result_string.empty?
    result_string
  end
end

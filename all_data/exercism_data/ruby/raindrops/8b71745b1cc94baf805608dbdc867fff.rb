class Raindrops
  
  def self.convert(num)
    translation = ''  
    translation << "Pling" if num % 3 == 0
    translation << "Plang" if num % 5 == 0
    translation << "Plong" if num % 7 == 0
    translation << num.to_s if translation.empty?
    translation
  end
  
end

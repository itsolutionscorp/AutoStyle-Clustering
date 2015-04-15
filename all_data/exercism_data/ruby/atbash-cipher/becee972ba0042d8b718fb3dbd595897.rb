class Atbash
  ALPHA = "abcdefghijklmnopqrstuvwxyz"
  
  def self.encode(plain)
    m = ""
    plain.downcase.gsub(/[,.\s]/,'').split("").each { |letter|
      if ALPHA.index(letter) then
        m+=ALPHA[26-1-ALPHA.index(letter)] 
      else
        m+=letter
      end    
    }
    m.scan(/.{1,5}/).join(" ")
  end
  
  
end

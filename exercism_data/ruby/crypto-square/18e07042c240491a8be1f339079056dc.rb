class Crypto
  
  attr_accessor :input
  
  def initialize(input)
    self.input = input
    normalize_plaintext
  end  
  
  def normalize_plaintext
    self.input = self.input.downcase.gsub(/[^a-z0-9]/,"")
  end  
  
  def size
    Math.sqrt(self.input.size).ceil
  end  
  
  def plaintext_segments
    ret_val =[]
    self.input.split("").each_slice(size) { |s| ret_val << s.join("")}
    ret_val
  end  
  
  def ciphertext
    pt = plaintext_segments
    ret_val = []
    size.times do |row|
      str = []
      size.times do |col|
        str << pt[col][row] if pt[col]  
      end
      ret_val << str.join("")
    end  
    ret_val.join("")
  end  
  
  def normalize_ciphertext
    ret_val =[]
    ciphertext.split("").each_slice(5) { |s| ret_val << s.join("")}
    ret_val.join(" ")
  end 
 
  
end  

class Hamming
  def self.compute(a,b)
    a.length == b.length ? a.split(//).zip(b.split(//)).select{|a,b| a != b}.length : nil
  end  
end

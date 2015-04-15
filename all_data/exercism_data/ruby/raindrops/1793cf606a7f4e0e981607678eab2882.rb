module Raindrops
  
  def self.convert no
    String(RaindropNumber.new(no))
  end
  
  class RaindropNumber
    
    def initialize no
      @no = no
    end
    
    def to_s
      response = ""
      response << "Pling" if pling?
      response << "Plang" if plang?
      response << "Plong" if plong?
      response.empty? ? String(@no) : response
    end
    
    private
    
    def pling?
      @no % 3 == 0
    end
    
    def plang?
      @no % 5 == 0
    end
    
    def plong?
      @no % 7 == 0
    end
    
  end
  
end
